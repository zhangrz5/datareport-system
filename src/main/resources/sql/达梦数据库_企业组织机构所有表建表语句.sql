-- 企业组织机构相关表 - 达梦数据库建表语句汇总
-- 生成时间: 2025-11-18 07:40:56
-- 包含表数量: 6 个
-- 作者: 自动生成

-- 组织信息（SASAOrganizations）表
-- 达梦数据库建表语句
CREATE TABLE SASAORGANIZATIONS (
    ID VARCHAR(36) NOT NULL,                                                     -- 单位ID；主键，36位UUID
    CODE VARCHAR(256) NOT NULL,                                                  -- 单位编号；9位的原组织机构代码，取统一社会信用代码的9~17位
    NAME_CHS VARCHAR(512) NOT NULL,                                              -- 单位名称
    SHORTNAME_CHS VARCHAR(64) NOT NULL,                                          -- 单位简称
    SCC VARCHAR(18) NOT NULL,                                                    -- 统一社会信用代码；18位
    NAME_EN VARCHAR(512) ,                                                       -- 英文全称
    SHORTNAME_EN VARCHAR(64) ,                                                   -- 英文简称
    ORGFORM VARCHAR(36) NOT NULL,                                                -- 组织形式；详见《企业组织机构基本信息指标编码表》
    COMPANYTYPE VARCHAR(36) NOT NULL,                                            -- 企业类型；详见《企业组织机构基本信息指标编码表》
    UNITTYPE VARCHAR(36) NOT NULL,                                               -- 单位类型；详见《企业组织机构基本信息指标编码表》
    FOUNDDATE DATE NOT NULL,                                                     -- 成立日期；格式为年-月-日，如：2020-01-01
    OPERATEBEGIN DATE NOT NULL,                                                  -- 经营期限自；格式为年-月-日，如：2020-01-01
    OPERATEEND DATE NOT NULL,                                                    -- 经营期限至；格式为年-月-日，如：2020-01-01
    REGISTEREDCAPITAL NUMBER(22) NOT NULL,                                       -- 注册资本（万元）；不可超出4位小数
    SDCAPITAL NUMBER(18) NOT NULL,                                               -- 实缴资本（万元）；不可超出4位小数
    SDREGISTER NUMBER(18) NOT NULL,                                              -- 国有资本（万元）；不可超出4位小数
    EXT_51_LV9 NUMBER(38) NOT NULL,                                              -- 出资额（万元）；不可超出4位小数
    CURRENCY VARCHAR(36) NOT NULL,                                               -- 币别；详见《企业组织机构基本信息指标编码表》
    ISINANDOUTBOUNDARY VARCHAR(36) NOT NULL,                                     -- 境内境外标识；详见《企业组织机构基本信息指标编码表》
    OFCONTINENT VARCHAR(36) NOT NULL,                                            -- 所属大洲；详见《企业组织机构基本信息指标编码表》
    OFCOUNTRY VARCHAR(36) NOT NULL,                                              -- 所属国家；详见《企业组织机构基本信息指标编码表》
    OFREGION VARCHAR(36) NOT NULL,                                               -- 所在地区；详见《企业组织机构基本信息指标编码表》
    POSTADDRESSCLOB CLOB NOT NULL,                                               -- 注册地址
    BUSSCOPECLOB CLOB NOT NULL,                                                  -- 经营范围
    OFINDUSTRYMUTI VARCHAR(512) NOT NULL,                                        -- 所属行业；详见《企业组织机构基本信息指标编码表》 可多选，填写标准的行业中文名称，多个行业间用英文逗号隔开
    EXT_25_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否特殊目的公司；0：是； 1：否
    ISSHELLCOMPANY VARCHAR(8) NOT NULL,                                          -- 是否壳公司；0：是 1：否
    SHELLCOMTYPE VARCHAR(36) NOT NULL,                                           -- 空壳公司类别；详见《企业组织机构基本信息指标编码表》
    EXT_36_LV9 VARCHAR(1000) NOT NULL,                                           -- 法定代表人
    EXT_35_LV9 VARCHAR(1000) NOT NULL,                                           -- 联系电话
    EXT_53_LV9 NUMBER(38) NOT NULL,                                              -- 企业人数；整数
    PERSONNUM NUMBER(10) NOT NULL,                                                -- 员工人数；整数
    OFFICIALWEBSITE VARCHAR(512) ,                                               -- 官网
    ISONMARKET VARCHAR(8) NOT NULL,                                              -- 是否上市；0：否 1：是
    ISMANAGER VARCHAR(4) NOT NULL,                                               -- 是否管理主体；0：是 1：否
    DIRECTORATE VARCHAR(4) NOT NULL,                                             -- 是否建立董事会；1：是 0：否
    EXT_30_LV9 VARCHAR(1000) NOT NULL,                                           -- 领导人员管理体制；0：市委管理领导班子； 1：国资委党委管理领导班子； 2：市委管正职、国资委党委管副职； 3：其他
    GOVERNANCESTRUCTURE VARCHAR(4) NOT NULL,                                     -- 法人治理结构；0：已规范法人决策主体 1：未规范法人决策主体
    REQUIREMENTSFOREIGN VARCHAR(4) NOT NULL,                                     -- 境外企业特殊要求；0：境外环境具备报送条件 1：境外环境不具备报送条件 2：驻在地法律法规有特殊规定
    EXT_32_LV9 VARCHAR(1000) NOT NULL,                                           -- 国资监管机构；0：国务院国资委； 1：省国资委； 2：市国资委； 3：区国资办； 4：由本级政府授权的其他部门或者机构
    EXT_31_LV9 VARCHAR(1000) NOT NULL,                                           -- 与国家出资企业关系；0：国有全资企业； 1：国有绝对控股企业； 2：国有实际控制企业； 3：国有参股企业；
    IFGROUPUNIT VARCHAR(4) NOT NULL,                                             -- 是否集团单位；0：集团单位 1：下级单位
    EXT_27_LV9 VARCHAR(1000) NOT NULL,                                           -- 集团控参股情况；0：集团全资； 1：集团控股； 2：集团参股
    EXT_54_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否实控；0：是； 1：否
    EXT_26_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否交叉持股企业；0：是； 1：否
    EXT_34_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否存在个人代持股（含法人代持）；0：是； 1：否
    EXT_24_LV9 VARCHAR(1000) NOT NULL,                                           -- 产权登记状态；0：已登记； 1：未登记； 2：未办结
    EXT_33_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否科技企业；0：高新技术企业； 1：技术先进性服务企业； 2：科技型中小企业； 3：软件企业； 4：非科技企业； 5：其他科技企业
    PARENTCOMPANY VARCHAR(1024) NOT NULL,                                        -- 上级管理单位名称；集团的上级单位统一为国资委
    SDPNODEORG VARCHAR(64) NOT NULL,                                             -- 上级管理单位统一社会信用代码；18位
    MANAGELEVEL NUMBER(10) NOT NULL,                                              -- 管理层级；整数
    MEMBERSHIPGROUP VARCHAR(512) NOT NULL,                                       -- 所属集团名称
    SDORGNUMBER VARCHAR(30) NOT NULL,                                            -- 所属集团统一社会信用代码；18位
    PROPERTYRIGHTLEVEL VARCHAR(36) NOT NULL,                                     -- 企业产权级次（本单位法人层级）；整数
    MAJORINVESTOR VARCHAR(1024) NOT NULL,                                        -- 控股股东名称
    SDHOLDERORG VARCHAR(64) NOT NULL,                                            -- 控股股东统一社会信用代码；18位
    MAJORRATIO NUMBER(10) NOT NULL,                                              -- 控股股东控股比例；百分数，不可超出2位小数
    EXT_41_LV9 VARCHAR(1000) NOT NULL,                                           -- 上级单位名称
    EXT_40_LV9 VARCHAR(1000) NOT NULL,                                           -- 上级单位统一社会信用代码；18位
    EXT_52_LV9 NUMBER(38) NOT NULL,                                              -- 上级单位持股比例；百分数，不可超出2位小数
    CONACCOUNTS VARCHAR(4) NOT NULL,                                             -- 纳入合并报表；0：纳入 1：不纳入
    EXT_23_LV9 VARCHAR(1000) NOT NULL,                                           -- 会计核算方式；0：纳入并表范围企业； 1：按权益法核算的企业或参股投资； 2：按成本法核算的参股投资； 3：计入可供出售金融资产的参股投资； 4：已转让或注销； 5：未合并； 6：无长投； 7：获取固定分红实质非投资
    EXT_8_LV9 VARCHAR(1000) NOT NULL,                                            -- 报表类型；0：单户表； 1：集团差额表； 2：金融子企业表； 3：境外子企业表； 4：事业并企业表； 5：基建并企业表； 6：完全汇总表； 7：集团合并表； 8：选择汇总表
    EXT_2_LV9 VARCHAR(1000) NOT NULL,                                            -- 是否有财务报表数据；0：是； 1：否
    EXT_50_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否纳入结算标志；0：否 1：是
    EXT_20_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否四上企业；0：是； 1：否
    EXT_21_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否平台公司标志；0：否 1：是
    EXT_5_LV9 VARCHAR(1000) NOT NULL,                                            -- 融资平台类企业；0：市国资实际监管企业（含河西集团）； 1：市国资非实际控制企业； 2：市级非监管企业； 3：区国资实际监管企业； 4：区国资非实际控制企业； 5：区级非监管企业； 6：否
    EXT_1_LV9 VARCHAR(1000) NOT NULL,                                            -- 混企类别；0：混1； 1：混2； 2：混3； 3：混4； 4：其他
    BUSSCALE VARCHAR(36) NOT NULL,                                               -- 经营规模；0：大型； 1：中型； 2：小型； 3：微型
    MANAGEMENTFORM VARCHAR(36) NOT NULL,                                         -- 经营状态；详见《企业组织机构基本信息指标编码表》
    OPERATINGACTIVITIES VARCHAR(4) NOT NULL,                                     -- 经营活动情况；0：实际开展经营活动 1：未实际开展经营活动 2：计划股权转让
    FUNCAT VARCHAR(36) NOT NULL,                                                 -- 功能类别；0：商业一类； 1：商业二类； 2：公益类； 3：暂无分类
    ISMASTERBUSCLOB CLOB NOT NULL,                                               -- 本单位主营业务
    EXT_48_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否重点企业；0：是； 1：否
    EXT_45_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否员工持股企业；0：是； 1：否
    EXT_47_LV9 VARCHAR(1000) NOT NULL,                                           -- 是否混合所有制企业；0：是； 1：否
    EXT_44_LV9 VARCHAR(1000) NOT NULL,                                           -- "穿透式"混合所有制企业；0：是； 1：否             字段说明：若母公司是混改企业，其出资的各级子企业均为混改企业
    EXT_46_LV9 VARCHAR(1000) NOT NULL,                                           -- "非穿透式"混合所有制企业；0：是； 1：否 字段说明：各级独立法人中的非一人有限公司，且其股东层面有一个或以上非国有股东
    EXT_61_LV9 DATE ,                                                            -- 初次混改时间；混合所有制企业必填
    EXT_62_LV9 DATE ,                                                            -- 深化混改时间
    TIMESTAMP_CREATEDON TIMESTAMP NOT NULL,                                      -- 创建时间；企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00
    TIMESTAMP_LASTCHANGEDON TIMESTAMP NOT NULL,                                  -- 最后修改时间；企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00
    ADDTYPE VARCHAR(36) NOT NULL,                                                -- 新增类型；详见《企业组织机构基本信息指标编码表》
    CANCELTYPE VARCHAR(36) NOT NULL,                                             -- 注销类型；详见《企业组织机构基本信息指标编码表》
    REMARK VARCHAR(1024) ,                                                       -- 备注
    TIMESTAMP_CREATEDBY VARCHAR(256) NOT NULL,                                   -- 创建人
    TIMESTAMP_LASTCHANGEDBY VARCHAR(256) NOT NULL,                               -- 最后修改人
    IFDELETE CHAR(1) NOT NULL,                                                   -- 是否删除；0：否 1：是
    ISENABLE VARCHAR(36) NOT NULL,                                               -- 启用标识；0：停用 1：启用
    PATH_ISDETAIL CHAR(1) NOT NULL,                                              -- 是否明细；0：不是最末级 1：是最末级
    PATH_LAYER NUMBER(10) NOT NULL,                                               -- 级数；集团的级数统一为3，下级单位逐级递增
    PATH_PARENTELEMENT VARCHAR(36) NOT NULL,                                     -- 父节点；组织树中上级单位的统一社会信用代码
    PATH_SEQUENCE NUMBER(10) NOT NULL,                                            -- 顺序号；组织树中同一层级下企业的排列顺序
    PATHCODE_PATH VARCHAR(72) NOT NULL,                                          -- 分级码；集团分级码固定如下，下级单位在上级的基础上逐级增加4位流水码 城建集团：000100010001 交通集团：000100010002 地铁集团：000100010003 新工集团：000100010004 紫金集团：000100010005 旅游集团：000100010006 安居集团：000100010007 新农集团：000100010008 国资集团：000100010009 东南集团：00010001000A 河西集团：00010001000B 大数据集团00010001000C 体育集团：00010001000D 水务集团：00010001000E 国盛集团：00010001000F
    ENTERPRISE VARCHAR(4) NOT NULL,                                              -- 企业状态；0：手工录入 1：批量补全 2：手动补全 3：已校验 4：已完成 5：未处理 6：已处理 7：已审核 8：已报送
    AUDITSTATE VARCHAR(36) NOT NULL,                                             -- 审批状态；0：制单 1：提交审批 2：审批通过 3：审批不通过
    DATASTATE VARCHAR(32) NOT NULL,                                              -- 操作状态；0：原有 1：新增 2：修改 3：删除
    REPORTSTATE VARCHAR(32) NOT NULL,                                            -- 上报状态；SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报
    PROCESSSTATE VARCHAR(32) NOT NULL,                                           -- 流程状态；FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无
    CONSTRAINT PK_SASAORGANIZATIONS_ID PRIMARY KEY (ID)
);

-- 表注释
COMMENT ON TABLE SASAORGANIZATIONS IS '组织信息（SASAOrganizations）';

COMMENT ON COLUMN SASAORGANIZATIONS.ID IS '单位ID - 主键，36位UUID';
COMMENT ON COLUMN SASAORGANIZATIONS.CODE IS '单位编号 - 9位的原组织机构代码，取统一社会信用代码的9~17位';
COMMENT ON COLUMN SASAORGANIZATIONS.NAME_CHS IS '单位名称';
COMMENT ON COLUMN SASAORGANIZATIONS.SHORTNAME_CHS IS '单位简称';
COMMENT ON COLUMN SASAORGANIZATIONS.SCC IS '统一社会信用代码 - 18位';
COMMENT ON COLUMN SASAORGANIZATIONS.NAME_EN IS '英文全称';
COMMENT ON COLUMN SASAORGANIZATIONS.SHORTNAME_EN IS '英文简称';
COMMENT ON COLUMN SASAORGANIZATIONS.ORGFORM IS '组织形式 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.COMPANYTYPE IS '企业类型 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.UNITTYPE IS '单位类型 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.FOUNDDATE IS '成立日期 - 格式为年-月-日，如：2020-01-01';
COMMENT ON COLUMN SASAORGANIZATIONS.OPERATEBEGIN IS '经营期限自 - 格式为年-月-日，如：2020-01-01';
COMMENT ON COLUMN SASAORGANIZATIONS.OPERATEEND IS '经营期限至 - 格式为年-月-日，如：2020-01-01';
COMMENT ON COLUMN SASAORGANIZATIONS.REGISTEREDCAPITAL IS '注册资本（万元） - 不可超出4位小数';
COMMENT ON COLUMN SASAORGANIZATIONS.SDCAPITAL IS '实缴资本（万元） - 不可超出4位小数';
COMMENT ON COLUMN SASAORGANIZATIONS.SDREGISTER IS '国有资本（万元） - 不可超出4位小数';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_51_LV9 IS '出资额（万元） - 不可超出4位小数';
COMMENT ON COLUMN SASAORGANIZATIONS.CURRENCY IS '币别 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.ISINANDOUTBOUNDARY IS '境内境外标识 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.OFCONTINENT IS '所属大洲 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.OFCOUNTRY IS '所属国家 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.OFREGION IS '所在地区 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.POSTADDRESSCLOB IS '注册地址';
COMMENT ON COLUMN SASAORGANIZATIONS.BUSSCOPECLOB IS '经营范围';
COMMENT ON COLUMN SASAORGANIZATIONS.OFINDUSTRYMUTI IS '所属行业 - 详见《企业组织机构基本信息指标编码表》 可多选，填写标准的行业中文名称，多个行业间用英文逗号隔开';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_25_LV9 IS '是否特殊目的公司 - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.ISSHELLCOMPANY IS '是否壳公司 - 0：是 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.SHELLCOMTYPE IS '空壳公司类别 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_36_LV9 IS '法定代表人';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_35_LV9 IS '联系电话';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_53_LV9 IS '企业人数 - 整数';
COMMENT ON COLUMN SASAORGANIZATIONS.PERSONNUM IS '员工人数 - 整数';
COMMENT ON COLUMN SASAORGANIZATIONS.OFFICIALWEBSITE IS '官网';
COMMENT ON COLUMN SASAORGANIZATIONS.ISONMARKET IS '是否上市 - 0：否 1：是';
COMMENT ON COLUMN SASAORGANIZATIONS.ISMANAGER IS '是否管理主体 - 0：是 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.DIRECTORATE IS '是否建立董事会 - 1：是 0：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_30_LV9 IS '领导人员管理体制 - 0：市委管理领导班子； 1：国资委党委管理领导班子； 2：市委管正职、国资委党委管副职； 3：其他';
COMMENT ON COLUMN SASAORGANIZATIONS.GOVERNANCESTRUCTURE IS '法人治理结构 - 0：已规范法人决策主体 1：未规范法人决策主体';
COMMENT ON COLUMN SASAORGANIZATIONS.REQUIREMENTSFOREIGN IS '境外企业特殊要求 - 0：境外环境具备报送条件 1：境外环境不具备报送条件 2：驻在地法律法规有特殊规定';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_32_LV9 IS '国资监管机构 - 0：国务院国资委； 1：省国资委； 2：市国资委； 3：区国资办； 4：由本级政府授权的其他部门或者机构';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_31_LV9 IS '与国家出资企业关系 - 0：国有全资企业； 1：国有绝对控股企业； 2：国有实际控制企业； 3：国有参股企业；';
COMMENT ON COLUMN SASAORGANIZATIONS.IFGROUPUNIT IS '是否集团单位 - 0：集团单位 1：下级单位';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_27_LV9 IS '集团控参股情况 - 0：集团全资； 1：集团控股； 2：集团参股';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_54_LV9 IS '是否实控 - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_26_LV9 IS '是否交叉持股企业 - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_34_LV9 IS '是否存在个人代持股（含法人代持） - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_24_LV9 IS '产权登记状态 - 0：已登记； 1：未登记； 2：未办结';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_33_LV9 IS '是否科技企业 - 0：高新技术企业； 1：技术先进性服务企业； 2：科技型中小企业； 3：软件企业； 4：非科技企业； 5：其他科技企业';
COMMENT ON COLUMN SASAORGANIZATIONS.PARENTCOMPANY IS '上级管理单位名称 - 集团的上级单位统一为国资委';
COMMENT ON COLUMN SASAORGANIZATIONS.SDPNODEORG IS '上级管理单位统一社会信用代码 - 18位';
COMMENT ON COLUMN SASAORGANIZATIONS.MANAGELEVEL IS '管理层级 - 整数';
COMMENT ON COLUMN SASAORGANIZATIONS.MEMBERSHIPGROUP IS '所属集团名称';
COMMENT ON COLUMN SASAORGANIZATIONS.SDORGNUMBER IS '所属集团统一社会信用代码 - 18位';
COMMENT ON COLUMN SASAORGANIZATIONS.PROPERTYRIGHTLEVEL IS '企业产权级次（本单位法人层级） - 整数';
COMMENT ON COLUMN SASAORGANIZATIONS.MAJORINVESTOR IS '控股股东名称';
COMMENT ON COLUMN SASAORGANIZATIONS.SDHOLDERORG IS '控股股东统一社会信用代码 - 18位';
COMMENT ON COLUMN SASAORGANIZATIONS.MAJORRATIO IS '控股股东控股比例 - 百分数，不可超出2位小数';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_41_LV9 IS '上级单位名称';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_40_LV9 IS '上级单位统一社会信用代码 - 18位';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_52_LV9 IS '上级单位持股比例 - 百分数，不可超出2位小数';
COMMENT ON COLUMN SASAORGANIZATIONS.CONACCOUNTS IS '纳入合并报表 - 0：纳入 1：不纳入';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_23_LV9 IS '会计核算方式 - 0：纳入并表范围企业； 1：按权益法核算的企业或参股投资； 2：按成本法核算的参股投资； 3：计入可供出售金融资产的参股投资； 4：已转让或注销； 5：未合并； 6：无长投； 7：获取固定分红实质非投资';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_8_LV9 IS '报表类型 - 0：单户表； 1：集团差额表； 2：金融子企业表； 3：境外子企业表； 4：事业并企业表； 5：基建并企业表； 6：完全汇总表； 7：集团合并表； 8：选择汇总表';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_2_LV9 IS '是否有财务报表数据 - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_50_LV9 IS '是否纳入结算标志 - 0：否 1：是';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_20_LV9 IS '是否四上企业 - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_21_LV9 IS '是否平台公司标志 - 0：否 1：是';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_5_LV9 IS '融资平台类企业 - 0：市国资实际监管企业（含河西集团）； 1：市国资非实际控制企业； 2：市级非监管企业； 3：区国资实际监管企业； 4：区国资非实际控制企业； 5：区级非监管企业； 6：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_1_LV9 IS '混企类别 - 0：混1； 1：混2； 2：混3； 3：混4； 4：其他';
COMMENT ON COLUMN SASAORGANIZATIONS.BUSSCALE IS '经营规模 - 0：大型； 1：中型； 2：小型； 3：微型';
COMMENT ON COLUMN SASAORGANIZATIONS.MANAGEMENTFORM IS '经营状态 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.OPERATINGACTIVITIES IS '经营活动情况 - 0：实际开展经营活动 1：未实际开展经营活动 2：计划股权转让';
COMMENT ON COLUMN SASAORGANIZATIONS.FUNCAT IS '功能类别 - 0：商业一类； 1：商业二类； 2：公益类； 3：暂无分类';
COMMENT ON COLUMN SASAORGANIZATIONS.ISMASTERBUSCLOB IS '本单位主营业务';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_48_LV9 IS '是否重点企业 - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_45_LV9 IS '是否员工持股企业 - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_47_LV9 IS '是否混合所有制企业 - 0：是； 1：否';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_44_LV9 IS '"穿透式"混合所有制企业 - 0：是； 1：否             字段说明：若母公司是混改企业，其出资的各级子企业均为混改企业';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_46_LV9 IS '"非穿透式"混合所有制企业 - 0：是； 1：否 字段说明：各级独立法人中的非一人有限公司，且其股东层面有一个或以上非国有股东';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_61_LV9 IS '初次混改时间 - 混合所有制企业必填';
COMMENT ON COLUMN SASAORGANIZATIONS.EXT_62_LV9 IS '深化混改时间';
COMMENT ON COLUMN SASAORGANIZATIONS.TIMESTAMP_CREATEDON IS '创建时间 - 企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAORGANIZATIONS.TIMESTAMP_LASTCHANGEDON IS '最后修改时间 - 企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAORGANIZATIONS.ADDTYPE IS '新增类型 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.CANCELTYPE IS '注销类型 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAORGANIZATIONS.REMARK IS '备注';
COMMENT ON COLUMN SASAORGANIZATIONS.TIMESTAMP_CREATEDBY IS '创建人';
COMMENT ON COLUMN SASAORGANIZATIONS.TIMESTAMP_LASTCHANGEDBY IS '最后修改人';
COMMENT ON COLUMN SASAORGANIZATIONS.IFDELETE IS '是否删除 - 0：否 1：是';
COMMENT ON COLUMN SASAORGANIZATIONS.ISENABLE IS '启用标识 - 0：停用 1：启用';
COMMENT ON COLUMN SASAORGANIZATIONS.PATH_ISDETAIL IS '是否明细 - 0：不是最末级 1：是最末级';
COMMENT ON COLUMN SASAORGANIZATIONS.PATH_LAYER IS '级数 - 集团的级数统一为3，下级单位逐级递增';
COMMENT ON COLUMN SASAORGANIZATIONS.PATH_PARENTELEMENT IS '父节点 - 组织树中上级单位的统一社会信用代码';
COMMENT ON COLUMN SASAORGANIZATIONS.PATH_SEQUENCE IS '顺序号 - 组织树中同一层级下企业的排列顺序';
COMMENT ON COLUMN SASAORGANIZATIONS.PATHCODE_PATH IS '分级码 - 集团分级码固定如下，下级单位在上级的基础上逐级增加4位流水码 城建集团：000100010001 交通集团：000100010002 地铁集团：000100010003 新工集团：000100010004 紫金集团：000100010005 旅游集团：000100010006 安居集团：000100010007 新农集团：000100010008 国资集团：000100010009 东南集团：00010001000A 河西集团：00010001000B 大数据集团00010001000C 体育集团：00010001000D 水务集团：00010001000E 国盛集团：00010001000F';
COMMENT ON COLUMN SASAORGANIZATIONS.ENTERPRISE IS '企业状态 - 0：手工录入 1：批量补全 2：手动补全 3：已校验 4：已完成 5：未处理 6：已处理 7：已审核 8：已报送';
COMMENT ON COLUMN SASAORGANIZATIONS.AUDITSTATE IS '审批状态 - 0：制单 1：提交审批 2：审批通过 3：审批不通过';
COMMENT ON COLUMN SASAORGANIZATIONS.DATASTATE IS '操作状态 - 0：原有 1：新增 2：修改 3：删除';
COMMENT ON COLUMN SASAORGANIZATIONS.REPORTSTATE IS '上报状态 - SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报';
COMMENT ON COLUMN SASAORGANIZATIONS.PROCESSSTATE IS '流程状态 - FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无';

-- 索引建议：根据业务查询需求创建以下索引
-- CREATE INDEX IDX_SASAORGANIZATIONS_ID ON SASAORGANIZATIONS(ID);
-- CREATE INDEX IDX_SASAORGANIZATIONS_CODE ON SASAORGANIZATIONS(CODE);
-- CREATE INDEX IDX_SASAORGANIZATIONS_PERSONNUM ON SASAORGANIZATIONS(PERSONNUM);

----------------------------------------------------------------------------------------------------


-- 股权结构（SASAOwnership）表
-- 达梦数据库建表语句
CREATE TABLE SASAOWNERSHIP (
    PARENTID VARCHAR(36) NOT NULL,                                               -- 单位标识码；对应组织信息表中的统一社会信用代码
    ID VARCHAR(36) NOT NULL,                                                     -- 股东ID；主键，36位UUID
    STOCKHOLDER VARCHAR(1024) NOT NULL,                                          -- 股东名称
    OFCOUNTRY VARCHAR(36) NOT NULL,                                              -- 股东国别；详见《企业组织机构基本信息指标编码表》
    SHAREHOLDNATURE VARCHAR(36) NOT NULL,                                        -- 股东性质；详见《企业组织机构基本信息指标编码表》
    OWNERSHIPRATIO NUMBER(18) NOT NULL,                                          -- 股权比例；百分数，不可超出2位小数
    ORGFORM VARCHAR(36) NOT NULL,                                                -- 股东组织形式；详见《企业组织机构基本信息指标编码表》
    OWNERSHIPSCC VARCHAR(64) NOT NULL,                                           -- 股东统一社会信用代码；18位
    PAIDCAPITAL NUMBER(18) NOT NULL,                                             -- 实缴资本（万元）；不可超出4位小数
    SUBSCRIBEDCAPITAL NUMBER(18) NOT NULL,                                       -- 认缴资本（万元）；不可超出4位小数
    TIMESTAMP_CREATEDBY VARCHAR(256) NOT NULL,                                   -- 创建人
    TIMESTAMP_CREATEDON TIMESTAMP NOT NULL,                                      -- 创建时间；企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00
    TIMESTAMP_LASTCHANGEDBY VARCHAR(256) NOT NULL,                               -- 最后修改人
    TIMESTAMP_LASTCHANGEDON TIMESTAMP NOT NULL,                                  -- 最后修改时间；企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00
    DATASTATE VARCHAR(32) NOT NULL,                                              -- 操作状态；0：原有 1：新增 2：修改 3：删除
    REPORTSTATE VARCHAR(32) NOT NULL,                                            -- 上报状态；SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报
    PROCESSSTATE VARCHAR(32) NOT NULL,                                           -- 流程状态；FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无
    CONSTRAINT PK_SASAOWNERSHIP_ID PRIMARY KEY (ID)
);

-- 表注释
COMMENT ON TABLE SASAOWNERSHIP IS '股权结构（SASAOwnership）';

COMMENT ON COLUMN SASAOWNERSHIP.PARENTID IS '单位标识码 - 对应组织信息表中的统一社会信用代码';
COMMENT ON COLUMN SASAOWNERSHIP.ID IS '股东ID - 主键，36位UUID';
COMMENT ON COLUMN SASAOWNERSHIP.STOCKHOLDER IS '股东名称';
COMMENT ON COLUMN SASAOWNERSHIP.OFCOUNTRY IS '股东国别 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAOWNERSHIP.SHAREHOLDNATURE IS '股东性质 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAOWNERSHIP.OWNERSHIPRATIO IS '股权比例 - 百分数，不可超出2位小数';
COMMENT ON COLUMN SASAOWNERSHIP.ORGFORM IS '股东组织形式 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAOWNERSHIP.OWNERSHIPSCC IS '股东统一社会信用代码 - 18位';
COMMENT ON COLUMN SASAOWNERSHIP.PAIDCAPITAL IS '实缴资本（万元） - 不可超出4位小数';
COMMENT ON COLUMN SASAOWNERSHIP.SUBSCRIBEDCAPITAL IS '认缴资本（万元） - 不可超出4位小数';
COMMENT ON COLUMN SASAOWNERSHIP.TIMESTAMP_CREATEDBY IS '创建人';
COMMENT ON COLUMN SASAOWNERSHIP.TIMESTAMP_CREATEDON IS '创建时间 - 企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAOWNERSHIP.TIMESTAMP_LASTCHANGEDBY IS '最后修改人';
COMMENT ON COLUMN SASAOWNERSHIP.TIMESTAMP_LASTCHANGEDON IS '最后修改时间 - 企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAOWNERSHIP.DATASTATE IS '操作状态 - 0：原有 1：新增 2：修改 3：删除';
COMMENT ON COLUMN SASAOWNERSHIP.REPORTSTATE IS '上报状态 - SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报';
COMMENT ON COLUMN SASAOWNERSHIP.PROCESSSTATE IS '流程状态 - FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无';

-- 索引建议：根据业务查询需求创建以下索引
-- CREATE INDEX IDX_SASAOWNERSHIP_PARENTID ON SASAOWNERSHIP(PARENTID);
-- CREATE INDEX IDX_SASAOWNERSHIP_ID ON SASAOWNERSHIP(ID);
-- CREATE INDEX IDX_SASAOWNERSHIP_PAIDCAPITAL ON SASAOWNERSHIP(PAIDCAPITAL);

----------------------------------------------------------------------------------------------------


-- 对外投资（SASAEquityPart）表
-- 达梦数据库建表语句
CREATE TABLE SASAEQUITYPART (
    PARENTID VARCHAR(36) NOT NULL,                                               -- 单位标识码；对应组织信息表中的统一社会信用代码
    ID VARCHAR(36) NOT NULL,                                                     -- 被投资企业ID；主键，36位UUID
    PARTORG VARCHAR(512) NOT NULL,                                               -- 被投资企业
    PARTINANDOUTBOUND VARCHAR(36) NOT NULL,                                      -- 境内境外；详见《企业组织机构基本信息指标编码表》
    PARTIUNITTYPE VARCHAR(36) NOT NULL,                                          -- 单位类型；详见《企业组织机构基本信息指标编码表》
    PARTSHIPRATIO NUMBER(16) NOT NULL,                                           -- 持股比例；百分数，不可超出2位小数
    TIMESTAMP_CREATEDBY VARCHAR(256) NOT NULL,                                   -- 创建人
    TIMESTAMP_CREATEDON TIMESTAMP NOT NULL,                                      -- 创建时间；企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00
    TIMESTAMP_LASTCHANGEDBY VARCHAR(256) NOT NULL,                               -- 最后修改人
    TIMESTAMP_LASTCHANGEDON TIMESTAMP NOT NULL,                                  -- 最后修改时间；企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00
    DATASTATE VARCHAR(32) NOT NULL,                                              -- 操作状态；0：原有 1：新增 2：修改 3：删除
    REPORTSTATE VARCHAR(32) NOT NULL,                                            -- 上报状态；SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报
    PROCESSSTATE VARCHAR(32) NOT NULL,                                           -- 流程状态；FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无
    CONSTRAINT PK_SASAEQUITYPART_ID PRIMARY KEY (ID)
);

-- 表注释
COMMENT ON TABLE SASAEQUITYPART IS '对外投资（SASAEquityPart）';

COMMENT ON COLUMN SASAEQUITYPART.PARENTID IS '单位标识码 - 对应组织信息表中的统一社会信用代码';
COMMENT ON COLUMN SASAEQUITYPART.ID IS '被投资企业ID - 主键，36位UUID';
COMMENT ON COLUMN SASAEQUITYPART.PARTORG IS '被投资企业';
COMMENT ON COLUMN SASAEQUITYPART.PARTINANDOUTBOUND IS '境内境外 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAEQUITYPART.PARTIUNITTYPE IS '单位类型 - 详见《企业组织机构基本信息指标编码表》';
COMMENT ON COLUMN SASAEQUITYPART.PARTSHIPRATIO IS '持股比例 - 百分数，不可超出2位小数';
COMMENT ON COLUMN SASAEQUITYPART.TIMESTAMP_CREATEDBY IS '创建人';
COMMENT ON COLUMN SASAEQUITYPART.TIMESTAMP_CREATEDON IS '创建时间 - 企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAEQUITYPART.TIMESTAMP_LASTCHANGEDBY IS '最后修改人';
COMMENT ON COLUMN SASAEQUITYPART.TIMESTAMP_LASTCHANGEDON IS '最后修改时间 - 企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAEQUITYPART.DATASTATE IS '操作状态 - 0：原有 1：新增 2：修改 3：删除';
COMMENT ON COLUMN SASAEQUITYPART.REPORTSTATE IS '上报状态 - SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报';
COMMENT ON COLUMN SASAEQUITYPART.PROCESSSTATE IS '流程状态 - FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无';

-- 索引建议：根据业务查询需求创建以下索引
-- CREATE INDEX IDX_SASAEQUITYPART_PARENTID ON SASAEQUITYPART(PARENTID);
-- CREATE INDEX IDX_SASAEQUITYPART_ID ON SASAEQUITYPART(ID);

----------------------------------------------------------------------------------------------------


-- 人员信息（sasapersonnel）表
-- 达梦数据库建表语句
CREATE TABLE SASAPERSONNEL (
    PARENTID VARCHAR(36) NOT NULL,                                               -- 单位标识码；对应组织信息表中的统一社会信用代码
    ID VARCHAR(36) NOT NULL,                                                     -- 企业联系人ID；主键，36位UUID
    LINKMAN VARCHAR(64) NOT NULL,                                                -- 企业联系人
    LINKTELEPHONE VARCHAR(512) NOT NULL,                                         -- 企业联系人电话
    EMAIL VARCHAR(1024) NOT NULL,                                                -- 邮箱
    ISREPRESENTATIVE CHAR(1) NOT NULL,                                           -- 是否法人代表；1：是； 0：否
    TIMESTAMP_CREATEDBY VARCHAR(256) NOT NULL,                                   -- 创建人
    TIMESTAMP_CREATEDON TIMESTAMP NOT NULL,                                      -- 创建时间；企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00
    TIMESTAMP_LASTCHANGEDBY VARCHAR(256) NOT NULL,                               -- 最后修改人
    TIMESTAMP_LASTCHANGEDON TIMESTAMP NOT NULL,                                  -- 最后修改时间；企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00
    DATASTATE VARCHAR(32) NOT NULL,                                              -- 操作状态；0：原有 1：新增 2：修改 3：删除
    REPORTSTATE VARCHAR(32) NOT NULL,                                            -- 上报状态；SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报
    PROCESSSTATE VARCHAR(32) NOT NULL,                                           -- 流程状态；FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无
    CONSTRAINT PK_SASAPERSONNEL_ID PRIMARY KEY (ID)
);

-- 表注释
COMMENT ON TABLE SASAPERSONNEL IS '人员信息（sasapersonnel）';

COMMENT ON COLUMN SASAPERSONNEL.PARENTID IS '单位标识码 - 对应组织信息表中的统一社会信用代码';
COMMENT ON COLUMN SASAPERSONNEL.ID IS '企业联系人ID - 主键，36位UUID';
COMMENT ON COLUMN SASAPERSONNEL.LINKMAN IS '企业联系人';
COMMENT ON COLUMN SASAPERSONNEL.LINKTELEPHONE IS '企业联系人电话';
COMMENT ON COLUMN SASAPERSONNEL.EMAIL IS '邮箱';
COMMENT ON COLUMN SASAPERSONNEL.ISREPRESENTATIVE IS '是否法人代表 - 1：是； 0：否';
COMMENT ON COLUMN SASAPERSONNEL.TIMESTAMP_CREATEDBY IS '创建人';
COMMENT ON COLUMN SASAPERSONNEL.TIMESTAMP_CREATEDON IS '创建时间 - 企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAPERSONNEL.TIMESTAMP_LASTCHANGEDBY IS '最后修改人';
COMMENT ON COLUMN SASAPERSONNEL.TIMESTAMP_LASTCHANGEDON IS '最后修改时间 - 企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAPERSONNEL.DATASTATE IS '操作状态 - 0：原有 1：新增 2：修改 3：删除';
COMMENT ON COLUMN SASAPERSONNEL.REPORTSTATE IS '上报状态 - SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报';
COMMENT ON COLUMN SASAPERSONNEL.PROCESSSTATE IS '流程状态 - FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无';

-- 索引建议：根据业务查询需求创建以下索引
-- CREATE INDEX IDX_SASAPERSONNEL_PARENTID ON SASAPERSONNEL(PARENTID);
-- CREATE INDEX IDX_SASAPERSONNEL_ID ON SASAPERSONNEL(ID);

----------------------------------------------------------------------------------------------------


-- 附件信息（SASAOrgAttachment）表
-- 达梦数据库建表语句
CREATE TABLE SASAORGATTACHMENT (
    PARENTID VARCHAR(36) NOT NULL,                                               -- 单位标识码；对应组织信息表中的统一社会信用代码
    ID VARCHAR(36) NOT NULL,                                                     -- 附件ID
    ATTNAME VARCHAR(128) NOT NULL,                                               -- 附件名称
    ATTACHMENTINFO CLOB NOT NULL,                                                -- 附件信息
    TIMESTAMP_CREATEDBY VARCHAR(256) NOT NULL,                                   -- 创建人
    TIMESTAMP_CREATEDON TIMESTAMP NOT NULL,                                      -- 创建时间；企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00
    TIMESTAMP_LASTCHANGEDBY VARCHAR(256) NOT NULL,                               -- 最后修改人
    TIMESTAMP_LASTCHANGEDON TIMESTAMP NOT NULL,                                  -- 最后修改时间；企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00
    DATASTATE VARCHAR(32) NOT NULL,                                              -- 操作状态；0：原有 1：新增 2：修改 3：删除
    REPORTSTATE VARCHAR(32) NOT NULL,                                            -- 上报状态；SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报
    PROCESSSTATE VARCHAR(32) NOT NULL,                                           -- 流程状态；FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无
    CONSTRAINT PK_SASAORGATTACHMENT_ID PRIMARY KEY (ID)
);

-- 表注释
COMMENT ON TABLE SASAORGATTACHMENT IS '附件信息（SASAOrgAttachment）';

COMMENT ON COLUMN SASAORGATTACHMENT.PARENTID IS '单位标识码 - 对应组织信息表中的统一社会信用代码';
COMMENT ON COLUMN SASAORGATTACHMENT.ID IS '附件ID';
COMMENT ON COLUMN SASAORGATTACHMENT.ATTNAME IS '附件名称';
COMMENT ON COLUMN SASAORGATTACHMENT.ATTACHMENTINFO IS '附件信息';
COMMENT ON COLUMN SASAORGATTACHMENT.TIMESTAMP_CREATEDBY IS '创建人';
COMMENT ON COLUMN SASAORGATTACHMENT.TIMESTAMP_CREATEDON IS '创建时间 - 企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAORGATTACHMENT.TIMESTAMP_LASTCHANGEDBY IS '最后修改人';
COMMENT ON COLUMN SASAORGATTACHMENT.TIMESTAMP_LASTCHANGEDON IS '最后修改时间 - 企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASAORGATTACHMENT.DATASTATE IS '操作状态 - 0：原有 1：新增 2：修改 3：删除';
COMMENT ON COLUMN SASAORGATTACHMENT.REPORTSTATE IS '上报状态 - SSP_D：待上报 SSP_Y：已上报 SSP_T：退回 SSP_XD：修改待上报';
COMMENT ON COLUMN SASAORGATTACHMENT.PROCESSSTATE IS '流程状态 - FSP_N：核对未通过 FSP_Y：核对通过 FSP_D：待核对 TPS_W：无';

-- 索引建议：根据业务查询需求创建以下索引
-- CREATE INDEX IDX_SASAORGATTACHMENT_PARENTID ON SASAORGATTACHMENT(PARENTID);
-- CREATE INDEX IDX_SASAORGATTACHMENT_ID ON SASAORGATTACHMENT(ID);

----------------------------------------------------------------------------------------------------


-- 组织体系（SASALayerStr）表
-- 达梦数据库建表语句
CREATE TABLE SASALAYERSTR (
    PARENTID VARCHAR(36) NOT NULL,                                               -- 单位标识码；对应组织信息表中的统一社会信用代码
    ID VARCHAR(36) NOT NULL,                                                     -- 组织体系ID；主键，36位UUID
    ORGSYSID VARCHAR(36) NOT NULL,                                               -- 体系树；产权管理：49411e7b-1318-4573-b11f-84187e0b0efb 全面预算：6fcd26c4-5111-4484-bb0d-f96beccfb307 财务快报：ab0079ca-a040-4fa6-93f1-b275c7cbd9b5 财务决算：694913ce-f105-4df6-83e6-ae184699a5da 国有资产统计：50e15a1f-0d31-4e13-aef0-e9d72e22b2db 国有资本收益收缴：fd6ae4a5-fdf7-4f2c-9c54-1da950b2c49c 国有资本经营预算：a9c8ac49-f79c-415e-a700-d0ab681a55a1 投资监管：63aeaa4c-c9e6-453f-854c-1214b4c449cc 三重一大：99b4ad75-51e8-40ee-9f44-93c4e48361e9 安全生产：6924faf6-71c3-4b0d-82ed-1a3d89ea2a38
    PNODEID VARCHAR(36) NOT NULL,                                                -- 父节点；各体系树中上级单位的统一社会信用代码
    ISDETAIL VARCHAR(1) NOT NULL,                                                -- 是否明细；0：不是最末级 1：是最末级
    ISFIRST VARCHAR(1) NOT NULL,                                                 -- 是否一级；0：否 1：是
    LAYER NUMBER(10) NOT NULL,                                                    -- 体系树层级；各体系树中一级单位的层级为1，下级单位逐级递增
    SEQUENCE NUMBER(10) NOT NULL,                                                 -- 顺序号；各体系树中同一层级下企业的排列顺序
    REMARK VARCHAR(1024) NOT NULL,                                               -- 备注
    TIMESTAMP_CREATEDBY VARCHAR(256) NOT NULL,                                   -- 创建人
    TIMESTAMP_CREATEDON TIMESTAMP NOT NULL,                                      -- 创建时间；企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00
    TIMESTAMP_LASTCHANGEDBY VARCHAR(256) NOT NULL,                               -- 最后修改人
    TIMESTAMP_LASTCHANGEDON TIMESTAMP NOT NULL,                                  -- 最后修改时间；企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00
    CONSTRAINT PK_SASALAYERSTR_ID PRIMARY KEY (ID)
);

-- 表注释
COMMENT ON TABLE SASALAYERSTR IS '组织体系（SASALayerStr）';

COMMENT ON COLUMN SASALAYERSTR.PARENTID IS '单位标识码 - 对应组织信息表中的统一社会信用代码';
COMMENT ON COLUMN SASALAYERSTR.ID IS '组织体系ID - 主键，36位UUID';
COMMENT ON COLUMN SASALAYERSTR.ORGSYSID IS '体系树 - 产权管理：49411e7b-1318-4573-b11f-84187e0b0efb 全面预算：6fcd26c4-5111-4484-bb0d-f96beccfb307 财务快报：ab0079ca-a040-4fa6-93f1-b275c7cbd9b5 财务决算：694913ce-f105-4df6-83e6-ae184699a5da 国有资产统计：50e15a1f-0d31-4e13-aef0-e9d72e22b2db 国有资本收益收缴：fd6ae4a5-fdf7-4f2c-9c54-1da950b2c49c 国有资本经营预算：a9c8ac49-f79c-415e-a700-d0ab681a55a1 投资监管：63aeaa4c-c9e6-453f-854c-1214b4c449cc 三重一大：99b4ad75-51e8-40ee-9f44-93c4e48361e9 安全生产：6924faf6-71c3-4b0d-82ed-1a3d89ea2a38';
COMMENT ON COLUMN SASALAYERSTR.PNODEID IS '父节点 - 各体系树中上级单位的统一社会信用代码';
COMMENT ON COLUMN SASALAYERSTR.ISDETAIL IS '是否明细 - 0：不是最末级 1：是最末级';
COMMENT ON COLUMN SASALAYERSTR.ISFIRST IS '是否一级 - 0：否 1：是';
COMMENT ON COLUMN SASALAYERSTR.LAYER IS '体系树层级 - 各体系树中一级单位的层级为1，下级单位逐级递增';
COMMENT ON COLUMN SASALAYERSTR.SEQUENCE IS '顺序号 - 各体系树中同一层级下企业的排列顺序';
COMMENT ON COLUMN SASALAYERSTR.REMARK IS '备注';
COMMENT ON COLUMN SASALAYERSTR.TIMESTAMP_CREATEDBY IS '创建人';
COMMENT ON COLUMN SASALAYERSTR.TIMESTAMP_CREATEDON IS '创建时间 - 企业在系统中的创建时间，记录到秒，如：2020-01-01 00:00:00';
COMMENT ON COLUMN SASALAYERSTR.TIMESTAMP_LASTCHANGEDBY IS '最后修改人';
COMMENT ON COLUMN SASALAYERSTR.TIMESTAMP_LASTCHANGEDON IS '最后修改时间 - 企业在系统中的变更时间，记录到秒，如：2020-01-01 00:00:00';

-- 索引建议：根据业务查询需求创建以下索引
-- CREATE INDEX IDX_SASALAYERSTR_PARENTID ON SASALAYERSTR(PARENTID);
-- CREATE INDEX IDX_SASALAYERSTR_ID ON SASALAYERSTR(ID);
-- CREATE INDEX IDX_SASALAYERSTR_ORGSYSID ON SASALAYERSTR(ORGSYSID);

--  -------------------------------------------------------------------------------------------------
