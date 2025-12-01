-- ========================================
-- SASAORGANIZATIONS 组织信息表测试数据
-- ========================================

-- 1. 城建集团（一级集团）
INSERT INTO SASAORGANIZATIONS (
    ID, CODE, NAME_CHS, SHORTNAME_CHS, SCC, NAME_EN, SHORTNAME_EN,
    ORGFORM, COMPANYTYPE, UNITTYPE, FOUNDDATE, OPERATEBEGIN, OPERATEEND,
    REGISTEREDCAPITAL, SDCAPITAL, SDREGISTER, EXT_51_LV9, CURRENCY,
    ISINANDOUTBOUNDARY, OFCONTINENT, OFCOUNTRY, OFREGION,
    POSTADDRESSCLOB, BUSSCOPECLOB, OFINDUSTRYMUTI, EXT_25_LV9,
    ISSHELLCOMPANY, SHELLCOMTYPE, EXT_36_LV9, EXT_35_LV9, EXT_53_LV9,
    PERSONNUM, OFFICIALWEBSITE, ISONMARKET, ISMANAGER, DIRECTORATE,
    EXT_30_LV9, GOVERNANCESTRUCTURE, REQUIREMENTSFOREIGN, EXT_32_LV9,
    EXT_31_LV9, IFGROUPUNIT, EXT_27_LV9, EXT_54_LV9, EXT_26_LV9,
    EXT_34_LV9, EXT_24_LV9, EXT_33_LV9, PARENTCOMPANY, SDPNODEORG,
    MANAGELEVEL, MEMBERSHIPGROUP, SDORGNUMBER, PROPERTYRIGHTLEVEL,
    MAJORINVESTOR, SDHOLDERORG, MAJORRATIO, EXT_41_LV9, EXT_40_LV9,
    EXT_52_LV9, CONACCOUNTS, EXT_23_LV9, EXT_8_LV9, EXT_2_LV9,
    EXT_50_LV9, EXT_20_LV9, EXT_21_LV9, EXT_5_LV9, EXT_1_LV9,
    BUSSCALE, MANAGEMENTFORM, OPERATINGACTIVITIES, FUNCAT, ISMASTERBUSCLOB,
    EXT_48_LV9, EXT_45_LV9, EXT_47_LV9, EXT_44_LV9, EXT_46_LV9,
    EXT_61_LV9, EXT_62_LV9, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDON,
    ADDTYPE, CANCELTYPE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_LASTCHANGEDBY,
    IFDELETE, ISENABLE, PATH_ISDETAIL, PATH_LAYER, PATH_PARENTELEMENT,
    PATH_SEQUENCE, PATHCODE_PATH, ENTERPRISE, AUDITSTATE, DATASTATE,
    REPORTSTATE, PROCESSSTATE
) VALUES (
             '11e9a8f8-9876-4321-b123-456789abcdef',  -- ID
             '123456789',  -- CODE (9位组织机构代码)
             '南京城市建设集团有限公司',  -- NAME_CHS
             '城建集团',  -- SHORTNAME_CHS
             '913201001234567890',  -- SCC (18位统一社会信用代码)
             'Nanjing Urban Construction Group Co., Ltd.',  -- NAME_EN
             'UCG',  -- SHORTNAME_EN
             'ORG_001',  -- ORGFORM (组织形式)
             'COM_001',  -- COMPANYTYPE (企业类型)
             'UNIT_001',  -- UNITTYPE (单位类型)
             TO_DATE('2000-01-01', 'YYYY-MM-DD'),  -- FOUNDDATE
             TO_DATE('2000-01-01', 'YYYY-MM-DD'),  -- OPERATEBEGIN
             TO_DATE('2050-12-31', 'YYYY-MM-DD'),  -- OPERATEEND
             500000.0000,  -- REGISTEREDCAPITAL (注册资本50亿)
             500000.0000,  -- SDCAPITAL (实缴资本)
             500000.0000,  -- SDREGISTER (国有资本)
             500000.0000,  -- EXT_51_LV9 (出资额)
             'CNY_001',  -- CURRENCY (人民币)
             'INBOUND_001',  -- ISINANDOUTBOUNDARY (境内)
             'ASIA_001',  -- OFCONTINENT (亚洲)
             'CHINA_001',  -- OFCOUNTRY (中国)
             'REGION_320100',  -- OFREGION (南京市)
             '江苏省南京市建邺区江东中路369号',  -- POSTADDRESSCLOB
             '城市基础设施建设；房地产开发；建筑工程施工；市政公用工程施工',  -- BUSSCOPECLOB
             '建筑业,房地产业',  -- OFINDUSTRYMUTI
             '1',  -- EXT_25_LV9 (非特殊目的公司)
             '1',  -- ISSHELLCOMPANY (非壳公司)
             'SHELL_NONE',  -- SHELLCOMTYPE
             '张三',  -- EXT_36_LV9 (法定代表人)
             '025-86688888',  -- EXT_35_LV9 (联系电话)
             5000,  -- EXT_53_LV9 (企业人数)
             5000,  -- PERSONNUM (员工人数)
             'http://www.njucg.com',  -- OFFICIALWEBSITE
             '0',  -- ISONMARKET (未上市)
             '0',  -- ISMANAGER (是管理主体)
             '1',  -- DIRECTORATE (已建立董事会)
             '0',  -- EXT_30_LV9 (市委管理领导班子)
             '0',  -- GOVERNANCESTRUCTURE (已规范法人决策主体)
             '0',  -- REQUIREMENTSFOREIGN (境外环境具备报送条件)
             '2',  -- EXT_32_LV9 (市国资委)
             '0',  -- EXT_31_LV9 (国有全资企业)
             '0',  -- IFGROUPUNIT (集团单位)
             '0',  -- EXT_27_LV9 (集团全资)
             '0',  -- EXT_54_LV9 (是实控)
             '1',  -- EXT_26_LV9 (非交叉持股)
             '1',  -- EXT_34_LV9 (不存在个人代持)
             '0',  -- EXT_24_LV9 (已登记)
             '4',  -- EXT_33_LV9 (非科技企业)
             '南京市国有资产监督管理委员会',  -- PARENTCOMPANY
             '913201000000000001',  -- SDPNODEORG (国资委代码)
             3,  -- MANAGELEVEL (管理层级3)
             '城建集团',  -- MEMBERSHIPGROUP
             '913201001234567890',  -- SDORGNUMBER
             '1',  -- PROPERTYRIGHTLEVEL (一级法人)
             '南京市国有资产监督管理委员会',  -- MAJORINVESTOR
             '913201000000000001',  -- SDHOLDERORG
             100.00,  -- MAJORRATIO (100%控股)
             '南京市国有资产监督管理委员会',  -- EXT_41_LV9
             '913201000000000001',  -- EXT_40_LV9
             100.00,  -- EXT_52_LV9
             '0',  -- CONACCOUNTS (纳入合并报表)
             '0',  -- EXT_23_LV9 (纳入并表范围企业)
             '7',  -- EXT_8_LV9 (集团合并表)
             '0',  -- EXT_2_LV9 (有财务报表数据)
             '1',  -- EXT_50_LV9 (纳入结算)
             '0',  -- EXT_20_LV9 (是四上企业)
             '0',  -- EXT_21_LV9 (非平台公司)
             '0',  -- EXT_5_LV9 (市国资实际监管企业)
             '4',  -- EXT_1_LV9 (其他)
             '0',  -- BUSSCALE (大型)
             'MANAGE_001',  -- MANAGEMENTFORM (正常经营)
             '0',  -- OPERATINGACTIVITIES (实际开展经营活动)
             '0',  -- FUNCAT (商业一类)
             '城市基础设施建设、房地产开发、建筑工程施工、市政公用工程施工',  -- ISMASTERBUSCLOB
             '0',  -- EXT_48_LV9 (是重点企业)
             '1',  -- EXT_45_LV9 (非员工持股)
             '1',  -- EXT_47_LV9 (非混合所有制)
             '1',  -- EXT_44_LV9 (非穿透式混改)
             '1',  -- EXT_46_LV9 (非非穿透式混改)
             NULL,  -- EXT_61_LV9 (初次混改时间)
             NULL,  -- EXT_62_LV9 (深化混改时间)
             TO_TIMESTAMP('2024-01-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),  -- TIMESTAMP_CREATEDON
             TO_TIMESTAMP('2024-11-28 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),  -- TIMESTAMP_LASTCHANGEDON
             'ADD_001',  -- ADDTYPE (新设)
             'CANCEL_NONE',  -- CANCELTYPE (未注销)
             '城建集团总部',  -- REMARK
             'admin',  -- TIMESTAMP_CREATEDBY
             'admin',  -- TIMESTAMP_LASTCHANGEDBY
             '0',  -- IFDELETE (未删除)
             '1',  -- ISENABLE (启用)
             '0',  -- PATH_ISDETAIL (不是最末级)
             3,  -- PATH_LAYER (级数为3)
             '913201000000000001',  -- PATH_PARENTELEMENT (父节点-国资委)
             1,  -- PATH_SEQUENCE (顺序号1)
             '000100010001',  -- PATHCODE_PATH (城建集团分级码)
             '4',  -- ENTERPRISE (已完成)
             '2',  -- AUDITSTATE (审批通过)
             '0',  -- DATASTATE (原有)
             'SSP_Y',  -- REPORTSTATE (已上报)
             'FSP_Y'  -- PROCESSSTATE (核对通过)
         );

-- 2. 城建集团下属子公司1
INSERT INTO SASAORGANIZATIONS (
    ID, CODE, NAME_CHS, SHORTNAME_CHS, SCC, NAME_EN, SHORTNAME_EN,
    ORGFORM, COMPANYTYPE, UNITTYPE, FOUNDDATE, OPERATEBEGIN, OPERATEEND,
    REGISTEREDCAPITAL, SDCAPITAL, SDREGISTER, EXT_51_LV9, CURRENCY,
    ISINANDOUTBOUNDARY, OFCONTINENT, OFCOUNTRY, OFREGION,
    POSTADDRESSCLOB, BUSSCOPECLOB, OFINDUSTRYMUTI, EXT_25_LV9,
    ISSHELLCOMPANY, SHELLCOMTYPE, EXT_36_LV9, EXT_35_LV9, EXT_53_LV9,
    PERSONNUM, OFFICIALWEBSITE, ISONMARKET, ISMANAGER, DIRECTORATE,
    EXT_30_LV9, GOVERNANCESTRUCTURE, REQUIREMENTSFOREIGN, EXT_32_LV9,
    EXT_31_LV9, IFGROUPUNIT, EXT_27_LV9, EXT_54_LV9, EXT_26_LV9,
    EXT_34_LV9, EXT_24_LV9, EXT_33_LV9, PARENTCOMPANY, SDPNODEORG,
    MANAGELEVEL, MEMBERSHIPGROUP, SDORGNUMBER, PROPERTYRIGHTLEVEL,
    MAJORINVESTOR, SDHOLDERORG, MAJORRATIO, EXT_41_LV9, EXT_40_LV9,
    EXT_52_LV9, CONACCOUNTS, EXT_23_LV9, EXT_8_LV9, EXT_2_LV9,
    EXT_50_LV9, EXT_20_LV9, EXT_21_LV9, EXT_5_LV9, EXT_1_LV9,
    BUSSCALE, MANAGEMENTFORM, OPERATINGACTIVITIES, FUNCAT, ISMASTERBUSCLOB,
    EXT_48_LV9, EXT_45_LV9, EXT_47_LV9, EXT_44_LV9, EXT_46_LV9,
    EXT_61_LV9, EXT_62_LV9, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDON,
    ADDTYPE, CANCELTYPE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_LASTCHANGEDBY,
    IFDELETE, ISENABLE, PATH_ISDETAIL, PATH_LAYER, PATH_PARENTELEMENT,
    PATH_SEQUENCE, PATHCODE_PATH, ENTERPRISE, AUDITSTATE, DATASTATE,
    REPORTSTATE, PROCESSSTATE
) VALUES (
             '22e9a8f8-9876-4321-b123-456789abcde1',
             '234567891',
             '南京市政建设有限公司',
             '市政建设',
             '913201001234567891',
             'Nanjing Municipal Construction Co., Ltd.',
             'NMC',
             'ORG_001', 'COM_001', 'UNIT_002',
             TO_DATE('2005-03-15', 'YYYY-MM-DD'),
             TO_DATE('2005-03-15', 'YYYY-MM-DD'),
             TO_DATE('2055-03-14', 'YYYY-MM-DD'),
             50000.0000, 50000.0000, 50000.0000, 50000.0000, 'CNY_001',
             'INBOUND_001', 'ASIA_001', 'CHINA_001', 'REGION_320100',
             '江苏省南京市鼓楼区中山北路200号',
             '市政基础设施工程施工；道路工程施工；桥梁工程施工',
             '建筑业',
             '1', '1', 'SHELL_NONE', '李四', '025-83456789', 800,
             800, 'http://www.njmc.com', '0', '1', '1',
             '1', '0', '0', '2', '0', '1', '0', '0', '1',
             '1', '0', '4', '南京城市建设集团有限公司', '913201001234567890',
             4, '城建集团', '913201001234567890', '2',
             '南京城市建设集团有限公司', '913201001234567890', 100.00,
             '南京城市建设集团有限公司', '913201001234567890', 100.00,
             '0', '0', '0', '0', '1', '0', '0', '0', '4',
             '1', 'MANAGE_001', '0', '0', '市政基础设施工程施工、道路桥梁建设',
             '1', '1', '1', '1', '1',
             NULL, NULL,
             TO_TIMESTAMP('2024-03-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             TO_TIMESTAMP('2024-11-28 10:30:00', 'YYYY-MM-DD HH24:MI:SS'),
             'ADD_001', 'CANCEL_NONE', '城建集团下属市政工程公司',
             'admin', 'admin', '0', '1', '1', 4,
             '913201001234567890', 1, '0001000100010001',
             '4', '2', '0', 'SSP_Y', 'FSP_Y'
         );

-- 3. 交通集团（一级集团）
INSERT INTO SASAORGANIZATIONS (
    ID, CODE, NAME_CHS, SHORTNAME_CHS, SCC, NAME_EN, SHORTNAME_EN,
    ORGFORM, COMPANYTYPE, UNITTYPE, FOUNDDATE, OPERATEBEGIN, OPERATEEND,
    REGISTEREDCAPITAL, SDCAPITAL, SDREGISTER, EXT_51_LV9, CURRENCY,
    ISINANDOUTBOUNDARY, OFCONTINENT, OFCOUNTRY, OFREGION,
    POSTADDRESSCLOB, BUSSCOPECLOB, OFINDUSTRYMUTI, EXT_25_LV9,
    ISSHELLCOMPANY, SHELLCOMTYPE, EXT_36_LV9, EXT_35_LV9, EXT_53_LV9,
    PERSONNUM, OFFICIALWEBSITE, ISONMARKET, ISMANAGER, DIRECTORATE,
    EXT_30_LV9, GOVERNANCESTRUCTURE, REQUIREMENTSFOREIGN, EXT_32_LV9,
    EXT_31_LV9, IFGROUPUNIT, EXT_27_LV9, EXT_54_LV9, EXT_26_LV9,
    EXT_34_LV9, EXT_24_LV9, EXT_33_LV9, PARENTCOMPANY, SDPNODEORG,
    MANAGELEVEL, MEMBERSHIPGROUP, SDORGNUMBER, PROPERTYRIGHTLEVEL,
    MAJORINVESTOR, SDHOLDERORG, MAJORRATIO, EXT_41_LV9, EXT_40_LV9,
    EXT_52_LV9, CONACCOUNTS, EXT_23_LV9, EXT_8_LV9, EXT_2_LV9,
    EXT_50_LV9, EXT_20_LV9, EXT_21_LV9, EXT_5_LV9, EXT_1_LV9,
    BUSSCALE, MANAGEMENTFORM, OPERATINGACTIVITIES, FUNCAT, ISMASTERBUSCLOB,
    EXT_48_LV9, EXT_45_LV9, EXT_47_LV9, EXT_44_LV9, EXT_46_LV9,
    EXT_61_LV9, EXT_62_LV9, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDON,
    ADDTYPE, CANCELTYPE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_LASTCHANGEDBY,
    IFDELETE, ISENABLE, PATH_ISDETAIL, PATH_LAYER, PATH_PARENTELEMENT,
    PATH_SEQUENCE, PATHCODE_PATH, ENTERPRISE, AUDITSTATE, DATASTATE,
    REPORTSTATE, PROCESSSTATE
) VALUES (
             '33e9a8f8-9876-4321-b123-456789abcde2',
             '345678912',
             '南京交通集团有限公司',
             '交通集团',
             '913201001234567892',
             'Nanjing Transportation Group Co., Ltd.',
             'NTG',
             'ORG_001', 'COM_001', 'UNIT_001',
             TO_DATE('2001-06-01', 'YYYY-MM-DD'),
             TO_DATE('2001-06-01', 'YYYY-MM-DD'),
             TO_DATE('2051-05-31', 'YYYY-MM-DD'),
             300000.0000, 300000.0000, 300000.0000, 300000.0000, 'CNY_001',
             'INBOUND_001', 'ASIA_001', 'CHINA_001', 'REGION_320100',
             '江苏省南京市玄武区龙蟠中路233号',
             '城市公共交通运营；出租车运营；长途客运；货物运输',
             '交通运输业,仓储业',
             '1', '1', 'SHELL_NONE', '王五', '025-84567890', 3000,
             3000, 'http://www.njtg.com', '0', '0', '1',
             '0', '0', '0', '2', '0', '0', '0', '0', '1',
             '1', '0', '4', '南京市国有资产监督管理委员会', '913201000000000001',
             3, '交通集团', '913201001234567892', '1',
             '南京市国有资产监督管理委员会', '913201000000000001', 100.00,
             '南京市国有资产监督管理委员会', '913201000000000001', 100.00,
             '0', '0', '7', '0', '1', '0', '0', '0', '4',
             '0', 'MANAGE_001', '0', '2', '城市公共交通运营、出租车运营、长途客运',
             '0', '1', '1', '1', '1',
             NULL, NULL,
             TO_TIMESTAMP('2024-01-10 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             TO_TIMESTAMP('2024-11-28 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'ADD_001', 'CANCEL_NONE', '交通集团总部',
             'admin', 'admin', '0', '1', '0', 3,
             '913201000000000001', 2, '000100010002',
             '4', '2', '0', 'SSP_Y', 'FSP_Y'
         );

-- 4. 混合所有制企业示例
INSERT INTO SASAORGANIZATIONS (
    ID, CODE, NAME_CHS, SHORTNAME_CHS, SCC, NAME_EN, SHORTNAME_EN,
    ORGFORM, COMPANYTYPE, UNITTYPE, FOUNDDATE, OPERATEBEGIN, OPERATEEND,
    REGISTEREDCAPITAL, SDCAPITAL, SDREGISTER, EXT_51_LV9, CURRENCY,
    ISINANDOUTBOUNDARY, OFCONTINENT, OFCOUNTRY, OFREGION,
    POSTADDRESSCLOB, BUSSCOPECLOB, OFINDUSTRYMUTI, EXT_25_LV9,
    ISSHELLCOMPANY, SHELLCOMTYPE, EXT_36_LV9, EXT_35_LV9, EXT_53_LV9,
    PERSONNUM, OFFICIALWEBSITE, ISONMARKET, ISMANAGER, DIRECTORATE,
    EXT_30_LV9, GOVERNANCESTRUCTURE, REQUIREMENTSFOREIGN, EXT_32_LV9,
    EXT_31_LV9, IFGROUPUNIT, EXT_27_LV9, EXT_54_LV9, EXT_26_LV9,
    EXT_34_LV9, EXT_24_LV9, EXT_33_LV9, PARENTCOMPANY, SDPNODEORG,
    MANAGELEVEL, MEMBERSHIPGROUP, SDORGNUMBER, PROPERTYRIGHTLEVEL,
    MAJORINVESTOR, SDHOLDERORG, MAJORRATIO, EXT_41_LV9, EXT_40_LV9,
    EXT_52_LV9, CONACCOUNTS, EXT_23_LV9, EXT_8_LV9, EXT_2_LV9,
    EXT_50_LV9, EXT_20_LV9, EXT_21_LV9, EXT_5_LV9, EXT_1_LV9,
    BUSSCALE, MANAGEMENTFORM, OPERATINGACTIVITIES, FUNCAT, ISMASTERBUSCLOB,
    EXT_48_LV9, EXT_45_LV9, EXT_47_LV9, EXT_44_LV9, EXT_46_LV9,
    EXT_61_LV9, EXT_62_LV9, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDON,
    ADDTYPE, CANCELTYPE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_LASTCHANGEDBY,
    IFDELETE, ISENABLE, PATH_ISDETAIL, PATH_LAYER, PATH_PARENTELEMENT,
    PATH_SEQUENCE, PATHCODE_PATH, ENTERPRISE, AUDITSTATE, DATASTATE,
    REPORTSTATE, PROCESSSTATE
) VALUES (
             '44e9a8f8-9876-4321-b123-456789abcde3',
             '456789123',
             '南京智慧交通科技有限公司',
             '智慧交通',
             '913201001234567893',
             'Nanjing Smart Transportation Technology Co., Ltd.',
             'NSTT',
             'ORG_001', 'COM_002', 'UNIT_002',
             TO_DATE('2018-08-20', 'YYYY-MM-DD'),
             TO_DATE('2018-08-20', 'YYYY-MM-DD'),
             TO_DATE('2068-08-19', 'YYYY-MM-DD'),
             20000.0000, 15000.0000, 12000.0000, 12000.0000, 'CNY_001',
             'INBOUND_001', 'ASIA_001', 'CHINA_001', 'REGION_320100',
             '江苏省南京市江宁区科学园弘景大道1号',
             '智能交通系统研发；软件开发；大数据服务；云计算服务',
             '软件和信息技术服务业,科学研究和技术服务业',
             '1', '1', 'SHELL_NONE', '赵六', '025-86789012', 200,
             200, 'http://www.njstt.com', '0', '1', '1',
             '1', '0', '0', '2', '1', '1', '1', '0', '1',
             '1', '0', '3', '南京交通集团有限公司', '913201001234567892',
             4, '交通集团', '913201001234567892', '2',
             '南京交通集团有限公司', '913201001234567892', 60.00,
             '南京交通集团有限公司', '913201001234567892', 60.00,
             '0', '0', '0', '0', '1', '0', '0', '0', '0',
             '2', 'MANAGE_001', '0', '0', '智能交通系统研发、软件开发、大数据服务',
             '1', '0', '0', '0', '0',
             TO_DATE('2018-08-20', 'YYYY-MM-DD'),
             TO_DATE('2022-05-15', 'YYYY-MM-DD'),
             TO_TIMESTAMP('2024-08-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             TO_TIMESTAMP('2024-11-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'ADD_002', 'CANCEL_NONE', '混合所有制改革试点企业，引入战略投资者',
             'admin', 'admin', '0', '1', '1', 4,
             '913201001234567892', 1, '0001000100020001',
             '4', '2', '0', 'SSP_Y', 'FSP_Y'
         );

-- 5. 上市公司示例
INSERT INTO SASAORGANIZATIONS (
    ID, CODE, NAME_CHS, SHORTNAME_CHS, SCC, NAME_EN, SHORTNAME_EN,
    ORGFORM, COMPANYTYPE, UNITTYPE, FOUNDDATE, OPERATEBEGIN, OPERATEEND,
    REGISTEREDCAPITAL, SDCAPITAL, SDREGISTER, EXT_51_LV9, CURRENCY,
    ISINANDOUTBOUNDARY, OFCONTINENT, OFCOUNTRY, OFREGION,
    POSTADDRESSCLOB, BUSSCOPECLOB, OFINDUSTRYMUTI, EXT_25_LV9,
    ISSHELLCOMPANY, SHELLCOMTYPE, EXT_36_LV9, EXT_35_LV9, EXT_53_LV9,
    PERSONNUM, OFFICIALWEBSITE, ISONMARKET, ISMANAGER, DIRECTORATE,
    EXT_30_LV9, GOVERNANCESTRUCTURE, REQUIREMENTSFOREIGN, EXT_32_LV9,
    EXT_31_LV9, IFGROUPUNIT, EXT_27_LV9, EXT_54_LV9, EXT_26_LV9,
    EXT_34_LV9, EXT_24_LV9, EXT_33_LV9, PARENTCOMPANY, SDPNODEORG,
    MANAGELEVEL, MEMBERSHIPGROUP, SDORGNUMBER, PROPERTYRIGHTLEVEL,
    MAJORINVESTOR, SDHOLDERORG, MAJORRATIO, EXT_41_LV9, EXT_40_LV9,
    EXT_52_LV9, CONACCOUNTS, EXT_23_LV9, EXT_8_LV9, EXT_2_LV9,
    EXT_50_LV9, EXT_20_LV9, EXT_21_LV9, EXT_5_LV9, EXT_1_LV9,
    BUSSCALE, MANAGEMENTFORM, OPERATINGACTIVITIES, FUNCAT, ISMASTERBUSCLOB,
    EXT_48_LV9, EXT_45_LV9, EXT_47_LV9, EXT_44_LV9, EXT_46_LV9,
    EXT_61_LV9, EXT_62_LV9, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDON,
    ADDTYPE, CANCELTYPE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_LASTCHANGEDBY,
    IFDELETE, ISENABLE, PATH_ISDETAIL, PATH_LAYER, PATH_PARENTELEMENT,
    PATH_SEQUENCE, PATHCODE_PATH, ENTERPRISE, AUDITSTATE, DATASTATE,
    REPORTSTATE, PROCESSSTATE
) VALUES (
             '55e9a8f8-9876-4321-b123-456789abcde4',
             '567891234',
             '南京新工投资集团股份有限公司',
             '新工投资',
             '913201001234567894',
             'Nanjing Xingong Investment Group Co., Ltd.',
             'NXIG',
             'ORG_002', 'COM_003', 'UNIT_001',
             TO_DATE('2010-10-10', 'YYYY-MM-DD'),
             TO_DATE('2010-10-10', 'YYYY-MM-DD'),
             TO_DATE('2060-10-09', 'YYYY-MM-DD'),
             800000.0000, 800000.0000, 480000.0000, 480000.0000, 'CNY_001',
             'INBOUND_001', 'ASIA_001', 'CHINA_001', 'REGION_320100',
             '江苏省南京市秦淮区中山东路288号新世纪广场',
             '股权投资；创业投资；资产管理；投资咨询',
             '金融业,商务服务业',
             '1', '1', 'SHELL_NONE', '孙七', '025-84123456', 1200,
             1200, 'http://www.njxgig.com', '1', '0', '1',
             '0', '0', '0', '2', '1', '0', '1', '0', '1',
             '1', '0', '4', '南京市国有资产监督管理委员会', '913201000000000001',
             3, '新工投资', '913201001234567894', '1',
             '南京市国有资产监督管理委员会', '913201000000000001', 60.00,
             '南京市国有资产监督管理委员会', '913201000000000001', 60.00,
             '0', '0', '7', '0', '1', '0', '0', '0', '0',
             '0', 'MANAGE_001', '0', '0', '股权投资、创业投资、资产管理',
             '0', '1', '0', '1', '0',
             TO_DATE('2016-03-15', 'YYYY-MM-DD'),
             NULL,
             TO_TIMESTAMP('2024-10-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             TO_TIMESTAMP('2024-11-28 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'ADD_001', 'CANCEL_NONE', '上海证券交易所上市公司，股票代码：600089',
             'admin', 'admin', '0', '1', '0', 3,
             '913201000000000001', 4, '000100010004',
             '4', '2', '0', 'SSP_Y', 'FSP_Y'
         );

-- ========================================
-- SASAOWNERSHIP 股权结构表测试数据
-- ========================================

-- 城建集团的股权结构（国资委100%持股）
INSERT INTO SASAOWNERSHIP (
    PARENTID, ID, STOCKHOLDER, OFCOUNTRY, SHAREHOLDNATURE,
    OWNERSHIPRATIO, ORGFORM, OWNERSHIPSCC, PAIDCAPITAL, SUBSCRIBEDCAPITAL,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567890',  -- PARENTID (城建集团)
             '11111111-1111-1111-1111-111111111111',  -- ID
             '南京市国有资产监督管理委员会',  -- STOCKHOLDER
             'CHINA_001',  -- OFCOUNTRY
             'SH_GOV',  -- SHAREHOLDNATURE (政府股东)
             100.00,  -- OWNERSHIPRATIO (100%股权)
             'ORG_GOV',  -- ORGFORM (政府机构)
             '913201000000000001',  -- OWNERSHIPSCC
             500000.0000,  -- PAIDCAPITAL (实缴50亿)
             500000.0000,  -- SUBSCRIBEDCAPITAL (认缴50亿)
             'admin',  -- TIMESTAMP_CREATEDBY
             TO_TIMESTAMP('2024-01-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 市政建设公司的股权结构（城建集团100%持股）
INSERT INTO SASAOWNERSHIP (
    PARENTID, ID, STOCKHOLDER, OFCOUNTRY, SHAREHOLDNATURE,
    OWNERSHIPRATIO, ORGFORM, OWNERSHIPSCC, PAIDCAPITAL, SUBSCRIBEDCAPITAL,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567891',
             '22222222-2222-2222-2222-222222222222',
             '南京城市建设集团有限公司',
             'CHINA_001',
             'SH_SOE',  -- SHAREHOLDNATURE (国有企业股东)
             100.00,
             'ORG_001',
             '913201001234567890',
             50000.0000,
             50000.0000,
             'admin',
             TO_TIMESTAMP('2024-03-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:30:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 智慧交通公司的股权结构（混合所有制：交通集团60% + 民营企业30% + 员工持股10%）
-- 股东1：交通集团60%
INSERT INTO SASAOWNERSHIP (
    PARENTID, ID, STOCKHOLDER, OFCOUNTRY, SHAREHOLDNATURE,
    OWNERSHIPRATIO, ORGFORM, OWNERSHIPSCC, PAIDCAPITAL, SUBSCRIBEDCAPITAL,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567893',
             '33333333-3333-3333-3333-333333333333',
             '南京交通集团有限公司',
             'CHINA_001',
             'SH_SOE',
             60.00,
             'ORG_001',
             '913201001234567892',
             9000.0000,  -- 实缴1.2亿的60% = 9000万
             12000.0000,  -- 认缴2亿的60% = 1.2亿
             'admin',
             TO_TIMESTAMP('2024-08-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 股东2：民营企业30%
INSERT INTO SASAOWNERSHIP (
    PARENTID, ID, STOCKHOLDER, OFCOUNTRY, SHAREHOLDNATURE,
    OWNERSHIPRATIO, ORGFORM, OWNERSHIPSCC, PAIDCAPITAL, SUBSCRIBEDCAPITAL,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567893',
             '44444444-4444-4444-4444-444444444444',
             '华为技术投资有限公司',
             'CHINA_001',
             'SH_PRIVATE',  -- SHAREHOLDNATURE (民营企业)
             30.00,
             'ORG_001',
             '913200005678901234',
             4500.0000,  -- 30%
             6000.0000,
             'admin',
             TO_TIMESTAMP('2024-08-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 股东3：员工持股平台10%
INSERT INTO SASAOWNERSHIP (
    PARENTID, ID, STOCKHOLDER, OFCOUNTRY, SHAREHOLDNATURE,
    OWNERSHIPRATIO, ORGFORM, OWNERSHIPSCC, PAIDCAPITAL, SUBSCRIBEDCAPITAL,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567893',
             '55555555-5555-5555-5555-555555555555',
             '南京智慧交通员工持股平台',
             'CHINA_001',
             'SH_EMPLOYEE',  -- SHAREHOLDNATURE (员工持股)
             10.00,
             'ORG_003',
             '913201009999999999',
             1500.0000,  -- 10%
             2000.0000,
             'admin',
             TO_TIMESTAMP('2024-08-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 新工投资的股权结构（国资委60% + 社会公众股40%）
-- 股东1：国资委60%
INSERT INTO SASAOWNERSHIP (
    PARENTID, ID, STOCKHOLDER, OFCOUNTRY, SHAREHOLDNATURE,
    OWNERSHIPRATIO, ORGFORM, OWNERSHIPSCC, PAIDCAPITAL, SUBSCRIBEDCAPITAL,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567894',
             '66666666-6666-6666-6666-666666666666',
             '南京市国有资产监督管理委员会',
             'CHINA_001',
             'SH_GOV',
             60.00,
             'ORG_GOV',
             '913201000000000001',
             480000.0000,  -- 60%
             480000.0000,
             'admin',
             TO_TIMESTAMP('2024-10-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 股东2：社会公众股40%
INSERT INTO SASAOWNERSHIP (
    PARENTID, ID, STOCKHOLDER, OFCOUNTRY, SHAREHOLDNATURE,
    OWNERSHIPRATIO, ORGFORM, OWNERSHIPSCC, PAIDCAPITAL, SUBSCRIBEDCAPITAL,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567894',
             '77777777-7777-7777-7777-777777777777',
             '社会公众股东',
             'CHINA_001',
             'SH_PUBLIC',  -- SHAREHOLDNATURE (公众股东)
             40.00,
             'ORG_PUBLIC',
             '000000000000000000',
             320000.0000,  -- 40%
             320000.0000,
             'admin',
             TO_TIMESTAMP('2024-10-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- ========================================
-- SASAEQUITYPART 对外投资表测试数据
-- ========================================

-- 城建集团对市政建设的投资
INSERT INTO SASAEQUITYPART (
    PARENTID, ID, PARTORG, PARTINANDOUTBOUND, PARTIUNITTYPE,
    PARTSHIPRATIO, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON,
    DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567890',  -- PARENTID (城建集团)
             'aaaa1111-1111-1111-1111-111111111111',
             '南京市政建设有限公司',  -- PARTORG (被投资企业)
             'INBOUND_001',  -- PARTINANDOUTBOUND (境内)
             'UNIT_002',  -- PARTIUNITTYPE (单位类型)
             100.00,  -- PARTSHIPRATIO (持股100%)
             'admin',
             TO_TIMESTAMP('2024-03-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:30:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 城建集团对房地产公司的投资
INSERT INTO SASAEQUITYPART (
    PARENTID, ID, PARTORG, PARTINANDOUTBOUND, PARTIUNITTYPE,
    PARTSHIPRATIO, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON,
    DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567890',
             'bbbb2222-2222-2222-2222-222222222222',
             '南京城建房地产开发有限公司',
             'INBOUND_001',
             'UNIT_002',
             100.00,
             'admin',
             TO_TIMESTAMP('2024-01-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 交通集团对智慧交通的投资
INSERT INTO SASAEQUITYPART (
    PARENTID, ID, PARTORG, PARTINANDOUTBOUND, PARTIUNITTYPE,
    PARTSHIPRATIO, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON,
    DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567892',  -- PARENTID (交通集团)
             'cccc3333-3333-3333-3333-333333333333',
             '南京智慧交通科技有限公司',
             'INBOUND_001',
             'UNIT_002',
             60.00,  -- 持股60%
             'admin',
             TO_TIMESTAMP('2024-08-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 交通集团对公交公司的投资
INSERT INTO SASAEQUITYPART (
    PARENTID, ID, PARTORG, PARTINANDOUTBOUND, PARTIUNITTYPE,
    PARTSHIPRATIO, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON,
    DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567892',
             'dddd4444-4444-4444-4444-444444444444',
             '南京公共交通集团有限公司',
             'INBOUND_001',
             'UNIT_002',
             100.00,
             'admin',
             TO_TIMESTAMP('2024-01-10 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 新工投资对科技公司的投资
INSERT INTO SASAEQUITYPART (
    PARENTID, ID, PARTORG, PARTINANDOUTBOUND, PARTIUNITTYPE,
    PARTSHIPRATIO, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON,
    DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567894',  -- PARENTID (新工投资)
             'eeee5555-5555-5555-5555-555555555555',
             '南京芯片产业投资基金',
             'INBOUND_001',
             'UNIT_003',
             35.00,  -- 参股35%
             'admin',
             TO_TIMESTAMP('2024-10-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 新工投资对境外企业的投资
INSERT INTO SASAEQUITYPART (
    PARENTID, ID, PARTORG, PARTINANDOUTBOUND, PARTIUNITTYPE,
    PARTSHIPRATIO, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON,
    DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567894',
             'ffff6666-6666-6666-6666-666666666666',
             'Nanjing Investment (Singapore) Pte. Ltd.',
             'OUTBOUND_001',  -- PARTINANDOUTBOUND (境外)
             'UNIT_004',
             80.00,
             'admin',
             TO_TIMESTAMP('2024-10-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- ========================================
-- SASAPERSONNEL 人员信息表测试数据
-- ========================================

-- 城建集团联系人
INSERT INTO SASAPERSONNEL (
    PARENTID, ID, LINKMAN, LINKTELEPHONE, EMAIL, ISREPRESENTATIVE,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567890',
             'pppp1111-1111-1111-1111-111111111111',
             '张三',  -- LINKMAN
             '025-86688888',  -- LINKTELEPHONE
             'zhangsan@njucg.com',  -- EMAIL
             '1',  -- ISREPRESENTATIVE (是法定代表人)
             'admin',
             TO_TIMESTAMP('2024-01-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

INSERT INTO SASAPERSONNEL (
    PARENTID, ID, LINKMAN, LINKTELEPHONE, EMAIL, ISREPRESENTATIVE,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567890',
             'qqqq2222-2222-2222-2222-222222222222',
             '陈经理',
             '025-86688889',
             'chen@njucg.com',
             '0',  -- 不是法定代表人
             'admin',
             TO_TIMESTAMP('2024-01-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 市政建设公司联系人
INSERT INTO SASAPERSONNEL (
    PARENTID, ID, LINKMAN, LINKTELEPHONE, EMAIL, ISREPRESENTATIVE,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567891',
             'rrrr3333-3333-3333-3333-333333333333',
             '李四',
             '025-83456789',
             'lisi@njmc.com',
             '1',
             'admin',
             TO_TIMESTAMP('2024-03-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:30:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 交通集团联系人
INSERT INTO SASAPERSONNEL (
    PARENTID, ID, LINKMAN, LINKTELEPHONE, EMAIL, ISREPRESENTATIVE,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567892',
             'ssss4444-4444-4444-4444-444444444444',
             '王五',
             '025-84567890',
             'wangwu@njtg.com',
             '1',
             'admin',
             TO_TIMESTAMP('2024-01-10 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

INSERT INTO SASAPERSONNEL (
    PARENTID, ID, LINKMAN, LINKTELEPHONE, EMAIL, ISREPRESENTATIVE,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567892',
             'tttt5555-5555-5555-5555-555555555555',
             '刘秘书',
             '025-84567891,13800138000',  -- 多个电话
             'liu@njtg.com',
             '0',
             'admin',
             TO_TIMESTAMP('2024-01-10 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 智慧交通公司联系人
INSERT INTO SASAPERSONNEL (
    PARENTID, ID, LINKMAN, LINKTELEPHONE, EMAIL, ISREPRESENTATIVE,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567893',
             'uuuu6666-6666-6666-6666-666666666666',
             '赵六',
             '025-86789012',
             'zhaoliu@njstt.com',
             '1',
             'admin',
             TO_TIMESTAMP('2024-08-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- 新工投资联系人
INSERT INTO SASAPERSONNEL (
    PARENTID, ID, LINKMAN, LINKTELEPHONE, EMAIL, ISREPRESENTATIVE,
    TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON, TIMESTAMP_LASTCHANGEDBY,
    TIMESTAMP_LASTCHANGEDON, DATASTATE, REPORTSTATE, PROCESSSTATE
) VALUES (
             '913201001234567894',
             'vvvv7777-7777-7777-7777-777777777777',
             '孙七',
             '025-84123456',
             'sunqi@njxgig.com',
             '1',
             'admin',
             TO_TIMESTAMP('2024-10-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             '0', 'SSP_Y', 'FSP_Y'
         );

-- ========================================
-- SASALAYERSTR 组织体系表测试数据
-- ========================================

-- 城建集团在产权管理体系树中的记录
INSERT INTO SASALAYERSTR (
    PARENTID, ID, ORGSYSID, PNODEID, ISDETAIL, ISFIRST,
    LAYER, SEQUENCE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON
) VALUES (
    '913201001234567890',  -- PARENTID
    'lay11111-1111-1111-1111-111111111111',  -- ID
    '49411e7b-1318-4573-b11f-84187e0b0efb',  -- ORGSYSID (产权管理)
    '913201000000000001',  -- PNODEID (父节点-国资委)
    '0',  -- ISDETAIL (不是最末级)
    '1',  -- ISFIRST (是一级)
    1,  -- LAYER (层级1)
    1,  -- SEQUENCE (顺序1)
    '城建集团在产权管理体系树中的位置',
    'admin',
    TO_TIMESTAMP('2024-01-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
    'admin',
    TO_TIMESTAMP('2024-11-28 10:00:00', 'YYYY-MM-DD HH24:MI:SS')
);

-- 城建集团在财务决算体系树中的记录
INSERT INTO SASALAYERSTR (
    PARENTID, ID, ORGSYSID, PNODEID, ISDETAIL, ISFIRST,
    LAYER, SEQUENCE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON
) VALUES (
             '913201001234567890',
             'lay22222-2222-2222-2222-222222222222',
             '694913ce-f105-4df6-83e6-ae184699a5da',  -- ORGSYSID (财务决算)
             '913201000000000001',
             '0',
             '1',
             1,
             1,
             '城建集团在财务决算体系树中的位置',
             'admin',
             TO_TIMESTAMP('2024-01-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:00:00', 'YYYY-MM-DD HH24:MI:SS')
         );

-- 市政建设公司在产权管理体系树中的记录
INSERT INTO SASALAYERSTR (
    PARENTID, ID, ORGSYSID, PNODEID, ISDETAIL, ISFIRST,
    LAYER, SEQUENCE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON
) VALUES (
             '913201001234567891',
             'lay33333-3333-3333-3333-333333333333',
             '49411e7b-1318-4573-b11f-84187e0b0efb',  -- 产权管理
             '913201001234567890',  -- PNODEID (父节点-城建集团)
             '1',  -- ISDETAIL (是最末级)
             '0',  -- ISFIRST (不是一级)
             2,  -- LAYER (层级2)
             1,  -- SEQUENCE
             '市政建设公司在产权管理体系树中的位置',
             'admin',
             TO_TIMESTAMP('2024-03-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 10:30:00', 'YYYY-MM-DD HH24:MI:SS')
         );

-- 交通集团在产权管理体系树中的记录
INSERT INTO SASALAYERSTR (
    PARENTID, ID, ORGSYSID, PNODEID, ISDETAIL, ISFIRST,
    LAYER, SEQUENCE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON
) VALUES (
             '913201001234567892',
             'lay44444-4444-4444-4444-444444444444',
             '49411e7b-1318-4573-b11f-84187e0b0efb',
             '913201000000000001',
             '0',
             '1',
             1,
             2,  -- SEQUENCE (顺序2，排在城建集团后面)
             '交通集团在产权管理体系树中的位置',
             'admin',
             TO_TIMESTAMP('2024-01-10 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 11:00:00', 'YYYY-MM-DD HH24:MI:SS')
         );

-- 交通集团在全面预算体系树中的记录
INSERT INTO SASALAYERSTR (
    PARENTID, ID, ORGSYSID, PNODEID, ISDETAIL, ISFIRST,
    LAYER, SEQUENCE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON
) VALUES (
             '913201001234567892',
             'lay55555-5555-5555-5555-555555555555',
             '6fcd26c4-5111-4484-bb0d-f96beccfb307',  -- ORGSYSID (全面预算)
             '913201000000000001',
             '0',
             '1',
             1,
             2,
             '交通集团在全面预算体系树中的位置',
             'admin',
             TO_TIMESTAMP('2024-01-10 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 11:00:00', 'YYYY-MM-DD HH24:MI:SS')
         );

-- 智慧交通在产权管理体系树中的记录
INSERT INTO SASALAYERSTR (
    PARENTID, ID, ORGSYSID, PNODEID, ISDETAIL, ISFIRST,
    LAYER, SEQUENCE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON
) VALUES (
             '913201001234567893',
             'lay66666-6666-6666-6666-666666666666',
             '49411e7b-1318-4573-b11f-84187e0b0efb',
             '913201001234567892',  -- PNODEID (父节点-交通集团)
             '1',  -- ISDETAIL (是最末级)
             '0',
             2,
             1,
             '智慧交通在产权管理体系树中的位置',
             'admin',
             TO_TIMESTAMP('2024-08-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS')
         );

-- 新工投资在产权管理体系树中的记录
INSERT INTO SASALAYERSTR (
    PARENTID, ID, ORGSYSID, PNODEID, ISDETAIL, ISFIRST,
    LAYER, SEQUENCE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON
) VALUES (
             '913201001234567894',
             'lay77777-7777-7777-7777-777777777777',
             '49411e7b-1318-4573-b11f-84187e0b0efb',
             '913201000000000001',
             '0',
             '1',
             1,
             4,  -- SEQUENCE (顺序4)
             '新工投资在产权管理体系树中的位置',
             'admin',
             TO_TIMESTAMP('2024-10-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 13:00:00', 'YYYY-MM-DD HH24:MI:SS')
         );

-- 新工投资在投资监管体系树中的记录
INSERT INTO SASALAYERSTR (
    PARENTID, ID, ORGSYSID, PNODEID, ISDETAIL, ISFIRST,
    LAYER, SEQUENCE, REMARK, TIMESTAMP_CREATEDBY, TIMESTAMP_CREATEDON,
    TIMESTAMP_LASTCHANGEDBY, TIMESTAMP_LASTCHANGEDON
) VALUES (
             '913201001234567894',
             'lay88888-8888-8888-8888-888888888888',
             '63aeaa4c-c9e6-453f-854c-1214b4c449cc',  -- ORGSYSID (投资监管)
             '913201000000000001',
             '0',
             '1',
             1,
             1,
             '新工投资在投资监管体系树中的位置',
             'admin',
             TO_TIMESTAMP('2024-10-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),
             'admin',
             TO_TIMESTAMP('2024-11-28 13:00:00', 'YYYY-MM-DD HH24:MI:SS')
         );

COMMIT;