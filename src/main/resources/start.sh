#!/bin/bash
# -------------------------------------------------------------
# JAR 应用程序管理脚本 (Start/Stop/Status)
# -------------------------------------------------------------

# 1. ===== 配置区域 START =====

# 检查 JAVA_HOME 是否已设置，如果没有，则手动指定 JDK 路径
if [ -z "$JAVA_HOME" ]; then
    # 请替换为您的 JDK 路径，例如 /opt/jdk21
    JAVA_HOME="/usr/local/java/jdk21"
fi

# 应用程序名称（请替换）
JAR_NAME="datareport-system-1.0.0-SNAPSHOT.jar"

# 应用程序运行所需的全部 JVM 参数和配置
JVM_OPTS="-Xms512m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

# 脚本工作目录
BASE_DIR=$(cd $(dirname $0); pwd)

# 日志文件路径
LOG_FILE="$BASE_DIR/log.log"

# PID 文件路径（用于记录进程ID）
PID_FILE="$BASE_DIR/app.pid"

# 2. ===== 核心函数区域 START =====

# 检查应用是否正在运行
check_status() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if [ -n "$PID" ]; then
            # 检查 PID 是否真的对应我们的 JAR 进程
            if ps -p $PID -f | grep "$JAR_NAME" | grep -v grep > /dev/null; then
                return 0 # 0 表示正在运行 (Running)
            fi
        fi
    fi
    return 1 # 1 表示未运行 (Not Running)
}

# 启动函数
start() {
    check_status
    if [ $? -eq 0 ]; then
        echo "✅ $JAR_NAME is already running! (PID: $PID)"
        return 0
    fi

    echo "Starting $JAR_NAME..."

    # 使用 nohup 在后台运行，并将输出重定向到 LOG_FILE
    nohup $JAVA_HOME/bin/java $JVM_OPTS -jar $BASE_DIR/$JAR_NAME > $LOG_FILE 2>&1 &

    # 获取并保存 PID
    PID=$!
    echo $PID > $PID_FILE

    sleep 2 # 等待几秒，确保进程稳定启动

    check_status
    if [ $? -eq 0 ]; then
        echo "🟢 $JAR_NAME started successfully. PID=$PID"
        echo "Log file: $LOG_FILE"
    else
        echo "🔴 $JAR_NAME failed to start. Check $LOG_FILE for details."
    fi
}

# 停止函数
stop() {
    check_status
    if [ $? -eq 1 ]; then
        echo "🚫 $JAR_NAME is not running."
        return 0
    fi

    echo "Stopping $JAR_NAME (PID: $PID)..."
    kill $PID

    # 循环等待进程结束
    TIMEOUT=10
    while kill -0 $PID 2>/dev/null; do
        if [ $TIMEOUT -le 0 ]; then
            echo "Timeout reached. Force killing (kill -9) the process $PID"
            kill -9 $PID
            break
        fi
        sleep 1
        TIMEOUT=$((TIMEOUT-1))
    done

    rm -f $PID_FILE
    echo "✅ $JAR_NAME stopped."
}

# 状态函数
status() {
    check_status
    if [ $? -eq 0 ]; then
        echo "🟢 $JAR_NAME is running. PID: $PID"
    else
        echo "🔴 $JAR_NAME is not running."
    fi
}

# 3. ===== 主执行逻辑 START =====

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    status)
        status
        ;;
    restart)
        stop
        start
        ;;
    *)
        echo "Usage: $0 {start|stop|status|restart}"
        exit 1
esac
exit 0