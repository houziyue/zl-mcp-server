package com.zlpay.enums;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public enum BankTypeEnum {

	/*
	 * 0102 中国工商银行
	 * 
	 * 0103 中国农业银行
	 * 
	 * 0104 中国银行
	 * 
	 * 0105 中国建设银行
	 * 
	 * 0301 交通银行
	 * 
	 * 0308 招商银行
	 * 
	 * 0310 上海浦东发展银行
	 * 
	 * 0305 中国民生银行
	 * 
	 * 0309 兴业银行
	 * 
	 * 0304 华夏银行
	 * 
	 * 0306 广东发展银行
	 * 
	 * 0307 深圳发展银行//平安银行吗？
	 * 
	 * 0302 中信银行
	 * 
	 * 0303 中国光大银行
	 */
	
	BANKTYPE_EPAY("0001", "华创证券","000000000001"),
	/**中国工商银行*/
	BANKTYPE_ICBC("0102","中国工商银行","102100099996" ,"C1010211000012"),
	/**中国农业银行*/
	BANKTYPE_ABC("0103","中国农业银行","103100000026" ,"C1010311000014"),
	/**中国银行*/
	BANKTYPE_BC("0104","中国银行","104100000004" ,"C1010411000013"),
	/**中国建设银行*/
	BANKTYPE_CCB("0105","中国建设银行","105100000017" ,"C1010511003703"),
	/**中国邮政储蓄银行*/
	BANKTYPE_PSBC("0106","中国邮政储蓄银行","403100000004" ,"C1040311005293"),
	/**中国交通银行*/
	BANKTYPE_BCM("0301","中国交通银行","301290000007" ,"C1030131001288"),
	/** 中国人民银行  */
	BANKTYPE_PBC("0999","中国人民银行","001666666666" ,"Z2012644000014"),
	UNION_PAY("0905","中国银联"),
	EPCC("0909","网联"),
	/**中信银行*/
	BANKTYPE_ECITIC("0302","中信银行","302100011000" ,"C1030211000389"), 
	/**中国光大银行*/
	BANKTYPE_CEB("0303","中国光大银行","303100000006" ,"C1030311000455"),
	/**华夏银行*/
	BANKTYPE_HXB("0304","华夏银行","304100040000" ,"C1030411000431"),
	/**中国民生银行*/
	BANKTYPE_CMBC("0305","中国民生银行","305100000013" ,"C1030511000483"),
	/**广东发展银行*/
	BANKTYPE_CGBC("0306","广东发展银行","306581000003" ,"C1030644021075"),
	/**平安银行**/
	BANKTYPE_PA("0307","平安银行","307584007998" ,"C1030744001296"),
	/**招商银行*/
	BANKTYPE_CMB("0308","招商银行","308584000013" ,"C1030844001362"), 
	/**兴业银行*/
	BANKTYPE_CIB("0309","兴业银行","309391000011" ,"C1030935001347"),
	/**上海浦东发展银行**/
	BANKTYPE_SPDB("0310","上海浦东发展银行","310290000013" ,"C1031031001312"),
	/**上海银行**/
	BANKTYPE_BS("0401","上海银行","325290000012" ,"C1091231000013"),
	/**北京银行**/
	BANKTYPE_BB("0403","北京银行","313100000013" ,"C1080211000028"),
	/**贵阳银行*/
	BANKTYPE_GYCCB("0313","贵阳银行","313701098010" ,"C1080552000033"),
	
	BANKTYPE_OTHER("9999","其他银行","000000000000"),
	/**贵州银行*/
	BANKTYPE_GZB("0314", "贵州银行","313701099012" ,"C1210952000015"),
	BANKTYPE_EGB("0315","恒丰银行","315456000105" ,"C1031537000883"),
	BANKTYPE_HANA("0597","韩亚银行","597100000014" ,"C1059711000029"),
	BANKTYPE_GZRCCB("0431","贵州省农村信用社联合社","402701002999" ,"C3223952000021"),
	BANKTYPE_SCRB("0322","上海农商行","322290000011" ,"C1122131000033"),
	BANKTYPE_CDRB("0554","成都农商行","314651000000" ,"C1258851000018"),
	BANKTYPE_LUZCCB("0555","泸州银行","313657092617" ,"C1079151000012"),
	BANKTYPE_BJCNCB("0412","北京农村商业银行","402100000018" ,"C1104311000033"),
	BANKTYPE_CBHB("0318","渤海银行","318110000014" ,"C1031812000205"),
	BANKTYPE_ZSCB("0317","浙商银行","316331000018" ,"C1031633000292"),
	BANKTYPE_HSB("0319","徽商银行","319361000013" ,"C1080434000027"),
	BANKTYPE_ZGCCB("0320","北京中关村银行股份有限公司","323100000012"),
	BANKTYPE_TJCB("0323","天津银行","313110000017" ,"C1080312000018"),
    BANKTYPE_BOCD("0519","成都银行","313651099999" ,"C1078851000017"),
	BANKTYPE_SJB("0396","盛京银行","313221030008" ,"C1080921000015"),
	BANKTYPE_BSCB("0332","包商银行","313192000013" ,"C1087615000019"),
	BANKTYPE_NJCB("0343","南京银行","313301008887" ,"C1086932000011"),
	BANKTYPE_JSCB("0344","江苏银行","313301099999" ,"C1086832000010"),

	BANKTYPE_QDCB("0359","青岛银行","313452060150" ,"C1088237000013"),

	BANKTYPE_NCBB("0397","四川天府银行股份有限公司","313673093259" ,"C1079651000043"),
	BANKTYPE_HDCB("0324","邯郸银行","313127000013" ,"C1088813000011"),
	BANKTYPE_XTCB("0325","邢台银行","313131000016" ,"C1088913000012"),
	BANKTYPE_ZJKCB("0326","张家口银行","313138000019" ,"C1089113000016"),
	BANKTYPE_CDCB("0327","承德银行","313141052422" ,"C1089213000029"),
	BANKTYPE_CZCB("0328","沧州银行","313143005157" ,"C1083413000016"),
	BANKTYPE_JSCCB("0329","晋商银行","313161000017" ,"C1090414000012"),
	BANKTYPE_JCCB("0330","晋城市商业银行","313168000003" ,"C1090714000015"),
	BANKTYPE_NMGCB("0331","内蒙古银行","313191000011" ,"C1087515000020"),
	BANKTYPE_EEDSCB("0333","鄂尔多斯银行","313205057830" ,"C1087815000011"),
	BANKTYPE_DLCB("0334","大连银行","313222080002" ,"C1081021000044"),
	BANKTYPE_ASCB("0335","鞍山市商业银行","313223007007" ,"C1081121000019"),
	BANKTYPE_JZCB("0336","锦州银行","313227000012" ,"C1081421000051"),
	BANKTYPE_HLDCB("0337","葫芦岛银行","313227600018" ,"C1082121000011"),
	BANKTYPE_YKCB("0338","营口银行","313228000276" ,"C1081521000025"),
	BANKTYPE_FXCB("0339","阜新银行","313229000008" ,"C1081621000014"),
	BANKTYPE_JLCB("0340","吉林银行","313241066661" ,"C1086722000029"),
	BANKTYPE_HEBCB("0341","哈尔滨银行","313261000018" ,"C1085523000014"),
	BANKTYPE_LJCB("0342","龙江银行","313261099913" ,"C1128823000011"),
	BANKTYPE_HZCB("0345","杭州银行","313331000014" ,"C1092033000015"),
	BANKTYPE_NBCB("0346","宁波银行","313332082914" ,"C1092133000016"),
	BANKTYPE_WZCB("0347","温州银行","313333007331" ,"C1092233000017"),
	BANKTYPE_HZCCB("0348","湖州银行","313336071575" ,"C1092433000021"),
	BANKTYPE_SXCB("0349","绍兴银行","313337009004" ,"C1092533000010"),
	BANKTYPE_ZJCZCB("0350","浙江稠州商业银行","313338707013" ,"C1092733000012"),
	BANKTYPE_TZCB("0351","台州银行","313345001665" ,"C1092833000025"),
	BANKTYPE_TLCCB("0352","浙江泰隆商业银行","313345010019" ,"C1092933000014"),
	BANKTYPE_MTCCB("0353","浙江民泰商业银行","313345400010" ,"C1093033000017"),
	BANKTYPE_HXCB("0354","福建海峡银行","313391080007" ,"C1082235000018"),
	BANKTYPE_XMCB("0355","厦门银行","313393080005" ,"C1082435000010"),
	BANKTYPE_NCCB("0356","江西银行","313421087506" ,"C1087436000012"),
	BANKTYPE_GZCB("0357","赣州银行","313428076517" ,"C1087336000011"),
	BANKTYPE_SRCB("0358","上饶银行","313433076801" ,"C1087236000010"),
	BANKTYPE_QSCB("0360","齐商银行","313453001017" ,"C1088337000014"),
	BANKTYPE_DYB("0361","东营银行","313455000018" ,"C1089437000017"),
	BANKTYPE_YTCB("0362","烟台银行","313456000108" ,"C1089537000020"),
	BANKTYPE_LFCB("0363","潍坊银行","313458000013" ,"C1089637000019"),
	BANKTYPE_JNCB("0364","济宁银行","313461000012" ,"C1089737000010"),
	BANKTYPE_TACB("0365","泰安银行","313463000993" ,"C1315837000018"),
	BANKTYPE_LSCB("0366","莱商银行","313463400019" ,"C1090137000016"),
	BANKTYPE_WSCB("0367","威海市商业银行","313465000010" ,"C1089937000012"),
	BANKTYPE_DZCB("0368","德州银行","313468000015" ,"C1165937000019"),
	BANKTYPE_LSCCB("0369","临商银行","313473070018" ,"C1090237000017"),
	BANKTYPE_RZCB("0370","日照银行","313473200011" ,"C1090037000015"),
	BANKTYPE_ZZCB("0371","郑州银行","313491000232" ,"C1085141000048"),
	BANKTYPE_KFCB("0372","开封商业银行","313492070005" ,"C1220741000010"),
	BANKTYPE_LYCB("0373","洛阳银行","313493080539" ,"C1084641000026"),
	BANKTYPE_LHCB("0374","漯河市商业银行","313504000010" ,"C1171041000016"),
	BANKTYPE_SQCB("0375","商丘市商业银行","313506082510" ,"C1127841000018"),
	BANKTYPE_NYCB("0376","南阳市商业银行","313513080408" ,"C3090241000015"),
	BANKTYPE_HKCB("0377","汉口银行","313521000011" ,"C1085642000133"),
	BANKTYPE_CSCB("0378","长沙银行","313551088886" ,"C1086243000029"),
	BANKTYPE_GCB("0379","广州银行","313581003284" ,"C1082744000038"),
	BANKTYPE_DGCB("0380","东莞银行","313602088017" ,"C1083144000010"),
	BANKTYPE_BBWCB("0381","广西北部湾银行","313611001018" ,"C1083245000012"),
	BANKTYPE_LZCB("0382","柳州银行","313614000012" ,"C1139245000018"),
	BANKTYPE_CQCB("0383","重庆银行","313653000013" ,"C1093150000010"),
	BANKTYPE_PZHCB("0384","攀枝花市商业银行","313656000019" ,"C1079051000011"),
	BANKTYPE_DYCB("0385","德阳银行","313658000014" ,"C3006151000011"),
	BANKTYPE_MYCB("0386","绵阳市商业银行","313659000016" ,"C1079551000016"),
	BANKTYPE_ALCB("0387","浙江网商银行","323331000001" ,"C1302533000018"),
	BANKTYPE_FDCB("0388","富滇银行","313731010015" ,"C1091753000015"),
	BANKTYPE_LZCCB("0389","兰州银行","313821001016" ,"C1082562000027"),
	BANKTYPE_QHCB("0390","青海银行","313851000018" ,"C1093363000017"),
	BANKTYPE_NXCB("0391","宁夏银行","313871000007" ,"C1087964000012"),
	BANKTYPE_WLMQCB("0392","乌鲁木齐银行","313881000002" ,"C1091365000015"),
	BANKTYPE_KLCB("0393","昆仑银行","313882000012" ,"C1137665000017"),
	BANKTYPE_SZCB("0394","苏州银行","314305006665" ,"C1115332000018"),
	BANKTYPE_HBCB("0395","河北银行","313121006888" ,"C1088513000020"),
	BANKTYPE_KSNCCB("0404","昆山农村商业银行","314305206650" ,"C1115532000022"),
	BANKTYPE_WJNCCB("0405","吴江农村商业银行","314305400015" ,"C1115832000013"),
	BANKTYPE_CSNCCB("0406","江苏常熟农村商业银行","314305506621" ,"C1115432000019"),
	BANKTYPE_ZJGCB("0407","张家港农村商业银行","314305670002" ,"C1115632000023"),
	BANKTYPE_GZNCCB("0408","广州农村商业银行","314581000011" ,"C1131244000017"),
	BANKTYPE_FSCB("0409","佛山顺德农村商业银行","314588000016" ,"C1131344000019"),
	BANKTYPE_QCNCCB("0410","重庆农村商业银行","314653000011" ,"C1126750000032"),
	BANKTYPE_TJCCB("0411","天津农村合作银行","317110010019" ,"C1104412000011"),

	BANKTYPE_JSNCCB("0413","江苏省农村信用社联合社","402301099998" ,"C3240632000017"),
	BANKTYPE_NBJZCB("0414","鄞州银行","402332010004" ,"C1123433000016"),
	BANKTYPE_AHNCCB("0415","安徽省农村信用联社","402361018886" ,"C3122934000014"),
	BANKTYPE_FJCNCB("0416","福建省农村信用社联合社","402391000068" ,"C3225935000012"),
	BANKTYPE_HBNCCB("0417","湖北省农村信用社联合社","402521000032" ,"C3235842000011"),
	BANKTYPE_SZNCCB("0418","深圳农村商业银行","402584009991" ,"C1109644000036"),
	BANKTYPE_DGNCCB("0419","东莞农村商业银行","402602000018" ,"C1131444000011"),
	BANKTYPE_GXNCCB("0420","广西壮族自治区农村信用社联合社","402611099974" ,"C3231945000143"),
	BANKTYPE_HNNCCB("0421","海南省农村信用社联合社","402641000014" ,"C3232146000011"),
	BANKTYPE_YNNCCB("0422","云南省农村信用社","402731057238" ,"C3252753000019"),
	BANKTYPE_CQTGB("0321","三峡银行","" ,"C1093250000011"),
	BANKTYPE_HBBCL("0423","湖北银行" ,"C1155842000015"),
	BANKTYPE_QLB("0424","齐鲁银行","313451000019" ,"C1088137000024"),
	BANKTYPE_JXNCCB("0425","江西省农村信用社" ,"" ,"C3136436000019"),
	BANKTYPE_HNRCCB("0426","湖南省农村信用社联合社","402551080008" ,"C3236743000013"),
	BANKTYPE_GLB("0427","桂林银行","313617000018" ,"C1140445000015"),
	BANKTYPE_GXRCCB("0428","广西农村信用社联合社" ,"" ,"C3231945000143"),
	BANKTYPE_YXCCB("0429","玉溪市商业银行","313741095715" ,"C3198053000017"),
	BANKTYPE_YNRCCB("0430","云南省农村信用联社","402731057238" ,"C3252753000019"),
	BANKTYPE_TJBHB("0527","天津滨海农村商业银行股份有限公司","314110000011" ,"C1104912000028"),
	BANKTYPE_LFB("0398","廊坊银行股份有限公司银行","313146000019" ,"C1083513000017"),
	BANKTYPE_JAB("0399","吉安农村商业银行股份有限公司","402421099990" ,"C1181236000012"),
	BANKTYPE_CCNCB("0400","长春农村商业银行股份有限公司","402241000015" ,"C1114022000150"),
	BANKTYPE_HCDFNCB("0402","河南登封农村商业银行股份有限公司","402491004011" ,"C1276041000012"),
	BANKTYPE_OCBC("0432","华侨银行（中国）有限公司","621290000011" ,""),
	BANKTYPE_JFZNCB("0433","景德镇农村商业银行股份有限公司","402421099990" ,"C1139436000013"),

	BANKTYPE_NSSYB("0434","乐山市商业银行股份有限公司","313665092924" ,"C1079351000014"),

	BANKTYPE_JJCCB("0435","九江银行","313424076706" ,"C1087136000021"),
	
	BANKTYPE_JZBANK("0436","晋中银行","313175000011" ,"C1090914000017"),

	BANKTYPE_SXCZBANK("0437","山西长子农村商业银行股份有限公司","402161002352" ,"C1252814000014"),
	BANKTYPE_XMBANK("0438","厦门农村商业银行股份有限公司","402391000068" ,"C1243435000018"),
	BANKTYPE_JTNSH("0439","吉林九台农村商业银行股份有限公司","402241000015" ,"C1114122000011"),

	BANKTYPE_JXGCNCB("0440","江西赣昌农村商业银行","402421099990" ,"C1270936000015"),
	
	BANKTYPE_GYSNCXYS("0441","巩义市农村信用合作联社","402491000026" ,"C1342041000012"),
	
	BANKTYPE_JSTCNCYH("0442","江苏太仓农村商业银行股份有限公司","314305106644" ,"C1115732000012"),
	
	BANKTYPE_YQSYYH("0443","阳泉市商业银行股份有限公司","313163000004" ,"C1090614000026"),
	
	BANKTYPE_QZYH("0444","泉州银行股份有限公司","313397075189" ,"C1082335000019"),
	
	BANKTYPE_CYYH("0445","朝阳银行股份有限公司","313234001089" ,"C1082021000010"),
	
	BANKTYPE_GDHXYH("0446","广东华兴银行股份有限公司","313586000006" ,"C1170944000010"),
	
	BANKTYPE_GZHZNCYH("0447","贵州花溪农村商业银行股份有限公司","402421099990" ,"C1265152000014"),

	BANKTYPE_HNLLNCYH("0448","湖南醴陵农村商业银行股份有限公司","402421099990" ,"C1319843000014"),

	BANKTYPE_ZYYH("0449","中原银行股份有限公司","402421099990" ,"C1290141000012"),

	BANKTYPE_JLDHNCYH("0450","吉林敦化农村商业银行股份有限公司","402421099990" ,"C1247722000011"),

	BANKTYPE_GZLGHMYH("0451","广州萝岗惠民村镇银行股份有限公司","402421099990"),

	BANKTYPE_YBNCYH("0452","延边农村商业银行股份有限公司","402421099990" ,"C1114422000026"),

	BANKTYPE_ASYH("0453","鞍山银行股份有限公司","402421099990" ,"C1081121000019"),

	BANKTYPE_HLNXHZS("0454","和龙市农村信用合作联社","402421099990" ,"C1315422000017"),

	BANKTYPE_HNQSNCYH("0455","河南确山农村商业银行股份有限公司","402421099990" ,"C1176741000016"),

	BANKTYPE_ANHXNCYH("0456","安徽歙县农村商业银行股份有限公司","402421099990" ,"C1186134000010"),

	BANKTYPE_JSSYYH("0457","江苏射阳农村商业银行股份有限公司","402421099990" ,"C1116632000013"),

	BANKTYPE_HSYH("0458","华商银行 ","402421099990" ,"C1078544000026"),

	BANKTYPE_CCFZNCYH("0459","长春发展农村商业银行股份有限公司 ","402421099990" ,"C1266022000010"),
	
	
	BANKTYPE_SXRFNCSY("0460","浙江绍兴瑞丰农村商业银行股份有限公司 ","402337110007" ,"C1124833000012"),
	
	BANKTYPE_HHNCSXYH("0461","黑河农村商业银行股份有限公司","402278000019" ,"C1237923000015"),

	BANKTYPE_SXLCSYYH("0462","山西潞城农村商业银行股份有限公司","313655091983" ,"C1218914000013"),

	BANKTYPE_SDFXNCSYYH("0463","山东费县农村商业银行股份有限公司","313655091983" ,"C1221637000011"),

	BANKTYPE_HRXJYH("0464","华融湘江银行股份有限公司","313655091983" ,"C1136043000016"),

	BANKTYPE_FANCXYS("0465","福安市农村信用合作联社","313655091983" ,"C3042035000014"),

	BANKTYPE_JSRMSYH("0466","江苏如皋农村商业银行股份有限公司","313655091983" ,"C1141032000010"),

	BANKTYPE_SXYDNCYH("0467","山西尧都农村商业银行股份有限公司","313655091983" ,"C1239614000012"),

	BANKTYPE_WXNCSYYH("0468","无锡农村商业银行股份有限公司","313655091983" ,"C1114632000020"),

	
	BANKTYPE_JSDFSYYH("0469","江苏大丰农村商业银行股份有限公司","313655091983" ,"C1116532000012"),

	
	BANKTYPE_HNAYNCYH("0470","河南安阳商都农村商业银行股份有限公司","313655091983" ,"C1276641000015"),

	BANKTYPE_JSZJNCYH("0471","江苏紫金农村商业银行股份有限公司","313655091983" ,"C1158932000019"),

	BANKTYPE_CBSNCSYYH("0472","长白山农村商业银行股份有限公司","313655091983" ,"C1369922000013"),

	BANKTYPE_NCSYSRYH("0473","上饶农村商业银行股份有限公司","313655091983" ,"C1269336000019"),

	BANKTYPE_ZHHRYH("0474","珠海华润银行股份有限公司","313585000990" ,"C1082944000016"),

	BANKTYPE_ZJLQNCYH("0475","浙江乐清农村商业银行股份有限公司","402331000007" ,"C1123833000010"),
	BANKTYPE_HHXZSYYH("0476","河南新郑农村商业银行股份有限公司","402491000026" ,"C1112541000014"),
	BANKTYPE_JZSYYH("0477","焦作市商业银行股份有限公司","313501080608" ,"C3085841000011"),
	BANKTYPE_HXZHYH("0478","徐州淮海农村商业银行股份有限公司","402301099998" ,"C1182132000012"),
	BANKTYPE_WZNCYH("0479","抚州农村商业银行股份有限公司","402421099990" ,"C1210636000016"),
	BANKTYPE_ZGSYYH("0480","自贡市商业银行股份有限公司","313655091983" ,"C1078951000018"),

	BANKTYPE_DLNCSYYH("0481","大连农村商业银行股份有限公司","402221010013" ,"C1206121000017"),
	BANKTYPE_SXXXNCYH("0482","山西夏县农村商业银行股份有限公司","402161002352" ,"C1279414000016"),
	BANKTYPE_HBTMNCYH("0483","湖北天门农村商业银行股份有限公司","402521000032" ,"C1305542000010"),
	BANKTYPE_ZSNCYH("0484","中山农村商业银行股份有限公司","402581090008" ,"C1226944000016"),
	BANKTYPE_DTYH("0485","大同银行股份有限公司","313162055820" ,"C1090514000013"),
	BANKTYPE_SXNX("0486","山西省农村信用社联合社","402161002352" ,"C3249814000016"),
	BANKTYPE_HBNX("0487","河北省农村信用社联合社","402121000009" ,"C3123013000012"),
	BANKTYPE_HNNX("0488","河南省农村信用社联合社","402491000026" ,"C3234641000015"),
	BANKTYPE_NMGNX("0489","内蒙古自治区农村信用社联合社","402191009992" ,"C3131015000017"),
	BANKTYPE_lNNX("0490","辽宁省农村信用社联合社","402221010013" ,"C3224121000015"),
	BANKTYPE_AHNX("0491","安徽省农村信用社联合社","402361018886" ,"C3122934000014"),
	BANKTYPE_ZJNX("0493","浙江省农村信用社联合社","402331000007" ,"C1449733000018"),
	BANKTYPE_JLNX("0494","吉林省农村信用社联合社","402241000015" ,"C3238322000018"),
	BANKTYPE_JXNX("0495","江西省农村信用社联合社","402421099990" ,"C3136436000019"),
	BANKTYPE_SDNX("0496","山东省农村信用社联合社","402451000010" ,"C3247537000016"),
	BANKTYPE_YNNX("0500","云南省农村信用社联合社","402731057238" ,"C3252753000019"),
	BANKTYPE_SHAANXINX("0501","陕西省农村信用社联合社","402791000010" ,"C3250661000014"),
	BANKTYPE_GXNX("0502","广西壮族自治区农村信用社","402611099974" ,"C3231945000143"),
	BANKTYPE_GZNX("0503","贵州省农村信用社","402701002999" ,"C3223952000021"),
	BANKTYPE_SCNX("0505","四川省农村信用社联合社","402651020006" ,"C3215651000019"),
	BANKTYPE_GDNX("0506","广东省农村信用社联合社","402581090008" ,"C3228644000016"),
	BANKTYPE_XJNX("0507","新疆维吾尔自治区农村信用社联合社","402881061690" ,"C3251665000010"),
	BANKTYPE_HNJNX("0598","黑龙江省农村信用社联合社","402278000019" ,"C3235023000010"),
	BANKTYPE_NNBCB("0599","宁波通商银行股份有限公司","313332090019" ,"C1077233000018"),
	BANKTYPE_FSNX("0508","抚顺银行","313224000015" ,"C1081221000010"),
	BANKTYPE_JJRCB("0509","江苏靖江农村商业银行股份有限公司","314312300004" ,"C1117232000011"),
	BANKTYPE_WHRCB("0497","武汉农村商业银行股份有限公司","402521090019" ,"C1112842000018"),
    BANKTYPE_WARCB("0498","贵州瓮安农村商业银行股份有限公司","314715542450" ,"C1266152000015"),
    BANKTYPE_BANKZCB("0499","遵义新蒲长征村镇银行","320703048011" ,"C1375352000011"),
    BANKTYPE_HUIHE("0510","新疆汇和银行股份有限公司","313898100016" ,"C1091665000018"),
    BANKTYPE_HTRCB("0511","内蒙古呼和浩特金谷农村商业银行股份有限公司","402191030166" ,"C1118615000028"),
    BANKTYPE_BOQHD("0512","秦皇岛银行股份有限公司","313126001022" ,"C1088713000010"),
    BANKTYYPE_BOTS("0513","唐山市商业银行股份有限公司","313124000018" ,"C1088613000019"),
    BANKTYYPE_HNBF("0514","河南宝丰农村商业银行股份有限公司","402495101011" ,"C1336941000013"),
    BANKTYPE_CRCB("0515","长沙农村商业银行股份有限公司","314551001010" ,"C1113243000027"),
    BANKTYPE_GDNYB("0516","广东南粤银行股份有限公司","313591001001" ,"C1083044000019"),
    BANKTYPE_HSTRCB("0517","合肥科技农村商业银行","402361018886" ,"C1105234000032"),
    BANKTYPE_BOGS("0518","甘肃银行股份有限公司","313821050016" ,"C1175662000017"),

    BANKTYPE_BOSX("0520","湖南三湘银行股份有限公司","323551000015" ,"C1362743000016"),
    BANKTYPE_QRCB("0521","青岛农村商业银行","402452000011" ,"C1194637000018"),
    BANKTYPE_BOXIAN("0522","西安银行股份有限公司","313791000015" ,"C1091161000033"),
    BANKTYPE_ADBOC("0523","中国农业发展银行","203100000027" ,"C1020311000163"),
    BANKTYPE_YNHT("0524","云南红塔银行股份有限公司","313741095715" ,"C1091953000017"),
    BANKTYPE_XMIB("0525","厦门国际银行股份有限公司","781393010011" ,"C1078135000084"),
    BANKTYPE_BOHN("0526","海南银行股份有限公司","313641099995" ,"C1309246000016"),
    BANKTYPE_QJCCB("0528","曲靖市商业银行股份有限公司","313736000027" ,"C1091853000016"),
    BANKTYPE_JNRCCB("0529","江南农村商业银行","314304083014" ,"C1128732000018"),
    BANKTYPE_JSCJCB("0530","江苏长江商业银行","313312300018" ,"C1087032000014"),
    BANKTYPE_JYRCB("0531","江苏江阴农村商业银行股份有限公司","314302200018" ,"C1114732000019"),
    BANKTYPE_BODD("0532","丹东银行","313226009000" ,"C1081321000011"),
    BANKTYPE_BOLY("0533","辽阳银行","313231000013" ,"C1081721000027"),
    BANKTYPE_BOJX("0534","嘉兴银行","313335081005" ,"C1092333000018"),
    BANKTYPE_JHB("0535","金华银行","313338009688" ,"C1092633000023"),
    BANKTYPE_ZZB("0536","枣庄银行","313454000016" ,"C1089337000016"),
    BANKTYPE_BOCTSJZ("0537","焦作中旅银行","313501080608" ,"C1083841000014"),
    BANKTYPE_BOPDS("0538","平顶山银行","313495081900" ,"C1141541000010"),
    BANKTYPE_GSRCB("0539","甘肃省农村信用社联合社","402821000015" ,"C3226362000012"),
    BANKTYPE_QHRCB("0540","青海农村信用社联合社","402851000016" ,"C3258063000017"),
    BANKTYPE_BOHS("0541","衡水银行","313148053964" ,"C1083613000018"),
    BANKTYPE_BOXJ("0542","新疆银行","313881088887" ,"C1364365000016"),
    BANKTYPE_HMCCB("0543","哈密市商业银行","313884000016" ,"C1229865000012"),
    BANKTYPE_BOKORLA("0544","库尔勒银行","313888000013" ,"C1091565000017"),
    BANKTYPE_BOWH("0545","乌海银行","313193057846" ,"C1087715000010"),
    BANKTYPE_BOCA("0546","长安银行","313791030003" ,"C1091061000018"),
    BANKTYPE_BOBD("0547","保定银行","313134000011" ,"C1089013000015"),
    BANKTYPE_YBCCB("0548","宜宾市商业银行","313671000017" ,"C1079251000013"),
    BANKTYPE_YACCB("0549","雅安市商业银行","313677000010" ,"C1079851000019"),
    BANKTYPE_SNCCB("0550","遂宁银行","313662000015" ,"C1080051000013"),
    BANKTYPE_HNRCB("0551","海口农村商业银行股份有限公司","314641003004" ,"C1182946000019"),
    BANKTYPE_NBDHB("0552","宁波东海银行","313332040018" ,"C1187333000137"),
    BANKTYPE_BOBX("0553","本溪市商业银行","313225000017" ,"C1143521000010"),
	BANKTYPE_CITIB("0311","花旗银行","531290088881" ,"C1053131004958"),
	BANKTYPE_CSZY("0556","赤水中银富登村镇银行有限公司","320704300019" ,"C1159742000017"),
	BANKTYPE_GZNS("0557","贵州农商行","314701012181" ,"C1188352000014"),
	BANKTYPE_HSBC("0558","汇丰银行","501100000011" ,"C1050131000117"),
	BANKTYPE_SZSB("0596","石嘴山银行","313872000017" ,"C1088064000015"),
	BANKTYPE_GYNS("0601","贵阳农村商业银行股份有限公司","314701012181" ,"C1188352000014"),
	BANKTYPE_SCXW("0602","四川新网银行股份有限公司","323651066666" ,"C1365451000019"),
	BANKTYPE_QHWZ("0603","深圳前海微众银行股份有限公司","323584000888" ,"C1291144000019"),
	BANKTYPE_GSHFM("0604","贵阳观山湖富民村镇银行股份有限公司","320701062015" ,"C1214952000019"),
	
	UNION_PAY_AUTH("0915","中国银联"),
	WECHAT("0906","微信" ,"" ,"Z2004944000010"),
	ALIPAY("0907","支付宝" ,"" ,"Z2007933000010"),
	BJUNION_PAY("0908","中国北京银联"),

	BANKTYPE_ALL("0000","全部"),
	
	BANKTYPE_NCPS("0002","金融联"),
	
	BANKTYPE_ZLZF("0003","证联支付"),
	
	BANKTYPE_OTCBB("0004","中证报价系统"),
	
	BANKTYPE_CMCC("0005","中国移动"),
	
	BANKTYPE_CTCC("0006","中国电信"),
	
	BANKTYPE_ABROAD("0007","境外银行通用行别"),
	
	BANKTYPE_HF("0010","华付"),
	
	BANKTYPE_SYBP("0011","中金"),
	
	BANKTYPE_CCXC("0012","中诚信"),
	
	BANKTYPE_BC_VIR("0114", "虚拟中国银行","104100000004"),
	
	BANKTYPE_CGBC_VIR("0316", "虚拟广东发展银行","306581000003"),
	
	BANKTYPE_HKBEA("0600", "东亚银行（中国）有限公司","502290000006" ,"C1050231004968"),

	UNION_PAY_XM("0910","厦门银联"),

	;	
	
	
	
	private String value;
	
	private final String displayName;
	
	/**总行联行号*/
	private String defaulBankNo;
	
	private String financeCode ; //金融机构编码
	
	private static Map<String, BankTypeEnum> valueMap = new HashMap<String, BankTypeEnum>();
	
	private static Map <String, BankTypeEnum> cnaps2banktypemap=new HashMap<String, BankTypeEnum>();

	static {
		for (BankTypeEnum _enum : BankTypeEnum.values()) {
			valueMap.put(_enum.value, _enum);
		}
		{
			cnaps2banktypemap.put("102", BANKTYPE_ICBC);
			cnaps2banktypemap.put("103", BANKTYPE_ABC);
			cnaps2banktypemap.put("104", BANKTYPE_BC);
			cnaps2banktypemap.put("105", BANKTYPE_CCB);
			cnaps2banktypemap.put("403", BANKTYPE_PSBC);
			cnaps2banktypemap.put("301", BANKTYPE_BCM);
			cnaps2banktypemap.put("302", BANKTYPE_ECITIC);
			cnaps2banktypemap.put("303", BANKTYPE_CEB);
			cnaps2banktypemap.put("304", BANKTYPE_HXB);
			cnaps2banktypemap.put("305", BANKTYPE_CMBC);
			cnaps2banktypemap.put("306", BANKTYPE_CGBC);
			cnaps2banktypemap.put("307", BANKTYPE_PA);
			cnaps2banktypemap.put("308", BANKTYPE_CMB);
			cnaps2banktypemap.put("309", BANKTYPE_CIB);
			cnaps2banktypemap.put("310", BANKTYPE_SPDB);
			cnaps2banktypemap.put("315", BANKTYPE_EGB);
			cnaps2banktypemap.put("316", BANKTYPE_ZSCB);
			cnaps2banktypemap.put("318", BANKTYPE_CBHB);
			cnaps2banktypemap.put("319", BANKTYPE_HSB);
			cnaps2banktypemap.put("322", BANKTYPE_SCRB);
			cnaps2banktypemap.put("531", BANKTYPE_CITIB);
			cnaps2banktypemap.put("597", BANKTYPE_HANA);
			cnaps2banktypemap.put("621", BANKTYPE_OCBC);
		}
	}

	static {
		for (BankTypeEnum _enum : BankTypeEnum.values()) {
			valueMap.put(_enum.value, _enum);
		}
	}

	/**
	* @Description:筛选出真实的银行
	* @return
	* @return BankType[]
	* @throws 
	* @author syuf   
	* @date 2016-6-27 下午6:51:04
	 */
	public static BankTypeEnum[] getRealBanks() {
		BankTypeEnum[] bankTypes = new BankTypeEnum[BankTypeEnum.values().length - 8];
		int i = 0;
		for(BankTypeEnum bankType : BankTypeEnum.values()){
			if(bankType == BANKTYPE_ALL || bankType == BANKTYPE_EPAY || bankType ==  BANKTYPE_NCPS 
					|| bankType == BANKTYPE_ZLZF || bankType == BANKTYPE_OTCBB || bankType == BANKTYPE_CMCC 
							|| bankType == BANKTYPE_CTCC || bankType == UNION_PAY || bankType == BANKTYPE_BC_VIR || bankType == BANKTYPE_CGBC_VIR){
				continue;
			}
			bankTypes[i] = bankType;
			i++;
		}
		return bankTypes;
	}
	/**
	* @Description: 交易所结算系统使用的银行卡列表
	* @return
	* @return BankType[]
	* @throws  
	* @author: zhangyq
	* @date: 2018-5-29 下午3:49:19 
	*/ 
	public static BankTypeEnum[] getStockBanks() {
		BankTypeEnum[] bankTypes = new BankTypeEnum[BankTypeEnum.values().length - 18];
		int i = 0;
		for(BankTypeEnum bankType : BankTypeEnum.values()){
			if(bankType == BANKTYPE_ALL || bankType == BANKTYPE_EPAY || bankType ==  BANKTYPE_NCPS 
					|| bankType == BANKTYPE_ZLZF || bankType == BANKTYPE_OTCBB || bankType == BANKTYPE_CMCC 
					|| bankType == BANKTYPE_CTCC || bankType ==BANKTYPE_ABROAD || bankType == BANKTYPE_HF
					|| bankType == BANKTYPE_SYBP || bankType ==BANKTYPE_CCXC || bankType ==BANKTYPE_BC_VIR 
					|| bankType ==BANKTYPE_CGBC_VIR	|| bankType == UNION_PAY || bankType ==WECHAT 
					|| bankType ==ALIPAY || bankType==BJUNION_PAY|| bankType==EPCC){
				continue;
			}
			bankTypes[i] = bankType;
			i++;
		}
		return bankTypes;
	}
	
	/**
	 * 实现了手工对账的银行
	 */
	public static BankTypeEnum[] listManualBillBanks() {
		return new BankTypeEnum[] { 	
				BANKTYPE_ICBC,    //中国工商银行
				BANKTYPE_ABC,     //中国农业银行
				BANKTYPE_CCB,     //中国建设银行
				BANKTYPE_EPAY,    //华创现金易
				UNION_PAY,		  //银联
				WECHAT,           //聚合支付-微信
				BJUNION_PAY,      //北京银联
				EPCC              //网联
		};   
	}

	BankTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	BankTypeEnum(String value, String displayName, boolean hasRefundNo) {
		this.value = value;
		this.displayName = displayName;
	}

	/**
	 * @param value
	 * @param displayName
	 * @param defaulBankNo
	 */
	private BankTypeEnum(String value, String displayName, String defaulBankNo) {
		this.value = value;
		this.displayName = displayName;
		this.defaulBankNo = defaulBankNo;
	}

	/**
	 *     新增 金融机构编码属性构造函数
	 * @Description:TODO
	 * @author: zhaofuxiang
	 * @date: 2020年3月9日 下午4:54:38  
	 * @param value
	 * @param displayName
	 * @param defaulBankNo
	 * @param financeCode
	 */
	private BankTypeEnum(String value, String displayName, String defaulBankNo, String financeCode) {
		this.value = value;
		this.displayName = displayName;
		this.defaulBankNo = defaulBankNo;
		this.financeCode = financeCode;
	}
	/**
	 * @return the defaulBankNo
	 */
	public String getDefaulBankNo() {
		return defaulBankNo;
	}

	/**
	 * @param defaulBankNo the defaulBankNo to set
	 */
	public void setDefaulBankNo(String defaulBankNo) {
		this.defaulBankNo = defaulBankNo;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	/**  
	* @return financeCode  
	*/
	public String getFinanceCode() {
		return financeCode;
	}
	/**  
	* @param financeCode 要设置的 financeCode  
	*/
	public void setFinanceCode(String financeCode) {
		this.financeCode = financeCode;
	}
	/**
	 * 移动支付所支持的银行
	 */
	public static BankTypeEnum[] getBankTypeMobile(){
	    BankTypeEnum[] bankTypes = new BankTypeEnum[] {
	            BANKTYPE_ICBC,BANKTYPE_ABC,BANKTYPE_BC,BANKTYPE_CCB,BANKTYPE_BCM,
	            BANKTYPE_CMB,BANKTYPE_SPDB,BANKTYPE_CMBC,BANKTYPE_CIB,BANKTYPE_HXB,
	            BANKTYPE_CGBC,BANKTYPE_PA,BANKTYPE_ECITIC,BANKTYPE_CEB
	    };
	    return bankTypes;
	}
	
	/**
	 * 是否支持退款流水号
	 * @description 
	 * @param bankType
	 * @return  
	 * @author yu.han
	 * @date 2013-6-19
	 */
	public static boolean hasRefundNo(String value) {
		BankTypeEnum[] bankTypes= new BankTypeEnum[] { BANKTYPE_CCB,BANKTYPE_BC,BANKTYPE_ABC,BANKTYPE_BCM,BANKTYPE_CMBC};
		BankTypeEnum type=BankTypeEnum.parseOf(value);
		for (BankTypeEnum bankType : bankTypes) {
			if(bankType.equals(type)){
				return false;
			}
		}
		return true;
	}
	/**
	 * 是否是后台解除授权
	 * @description 
	 * @param value
	 * @return  true 后台 false 前台
	 * @author yu.han
	 * @date 2013-7-3
	 */
	public static boolean isBackDelSign(String value) {
		BankTypeEnum[] bankTypes= new BankTypeEnum[] { BANKTYPE_BCM};
		BankTypeEnum type=BankTypeEnum.parseOf(value);
		for (BankTypeEnum bankType : bankTypes) {
			if(bankType.equals(type)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 是否支持后台授权
	 * @description 
	 * @param value
	 * @return  
	 * @author yu.han
	 * @date 2014-3-13
	 */
	public static boolean isBackSign(String value) {
		BankTypeEnum[] bankTypes= new BankTypeEnum[] { BANKTYPE_EPAY};
		BankTypeEnum type=BankTypeEnum.parseOf(value);
		for (BankTypeEnum bankType : bankTypes) {
			if(bankType.equals(type)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 需要转换的银行行别    备案上传汇总文件时所用
	 * @description：
	 * @param value
	 * @return  
	 * @author LiXiPing
	 * @date 2014-5-12
	 */
	public static boolean isTransform(String value){
		BankTypeEnum[] bankTypes= new BankTypeEnum[] {BANKTYPE_EPAY};
		BankTypeEnum type=BankTypeEnum.parseOf(value);
		for (BankTypeEnum bankType : bankTypes) {
			if(bankType.equals(type)){
				return true;
			}
		}
		return false;
	}
	
	
	
	@Override
	public String toString() {
		return this.getDisplayName();
	}
	

	/**
	 * 枚举转换
	 */
	public static BankTypeEnum parseOf(String value) {
		for (BankTypeEnum item : values())
			if (item.getValue().equals(value))
				return item;
		throw new IllegalArgumentException("枚举值[" + value + "]不匹配!");
	}
	
	/**
	 * 枚举转换
	 */
	public static BankTypeEnum parse(String value) {
		for (BankTypeEnum item : values())
			if (item.getValue().equals(value))
				return item;
		return null;
	}
	/**
	 * 根据联行号获取BankType
	 * @param bankNo	联行号
	 * @return
	 * @author	马颂超
	 * @date	2016年12月13日
	 */
	public static BankTypeEnum parseOfBankNo(String bankNo) {
		for (BankTypeEnum item : values())
			if (item.getDefaulBankNo()!=null && item.getDefaulBankNo().equals(bankNo))
				return item;
		return null;
	}
	public static BankTypeEnum parseOfFinanceCode(String financeCode) {
		for (BankTypeEnum item : values())
			if (item.getFinanceCode()!=null && item.getFinanceCode().equals(financeCode))
				return item;
		return null;
	}
	/**
	 * 获取枚举
	 */
	public static BankTypeEnum returnEnum(String value) {
		return valueMap.get(value);
	}

	public static Object getRechiBankTypes() {
		return new BankTypeEnum[] { 	
				BANKTYPE_CCB,     //中国建设银行
		};   
	}
	
	
	
	
	
	/**
	 * 基金需要合并的虚拟行别（不同扣款渠道使用相同的结算账户）
	 */
	public static BankTypeEnum[] getFundNetMergeBankType(){

		return new BankTypeEnum[] { 	
				BANKTYPE_CMCC,    //移动和包支付使用民生付款	
		};   
	
		
	}
	/**
	 * @desc 是否需要合并轧差的行别
	 * @return true 表示需要合并的行别，这些不上报监管
	 */
	public static Boolean ifFundNeedMergeBankType(String virBankType){

		BankTypeEnum[] list = getFundNetMergeBankType();
		for(BankTypeEnum banktype: list){
			if(banktype.getValue().equals(virBankType))
				return true;
		}
		return false;
	
		
	}
	/**
	 * @author zhaojf
	 * @param virBanktype  原始银行行别代码，这些行别的轧差数据不报送到监管行，属于被合并的数据
	 * @return  合并数据的行别代码
	 */
	public static BankTypeEnum  fundMergeNetBalanceBankType(BankTypeEnum virBanktype){
		
		/**
		 * 中移动与民生使用同一收款行别 合并数据
		 */
		if (BankTypeEnum.BANKTYPE_CMCC.equals(virBanktype)){
			
			return BankTypeEnum.BANKTYPE_CMBC;
			
		} else return null;
		
	}

	public static boolean isMobilePayment(String bankType) {
		return WECHAT.getValue().equals(bankType)||ALIPAY.getValue().equals(bankType);
	}

	/**
	 * 获取移动收单(聚合支付、微信支付宝收单)已接通的渠道方
	 * @return
	 * @author	马颂超
	 * @date	2017年5月3日
	 */
	public static BankTypeEnum[] getMobilePayProcessChannel(){
		return new BankTypeEnum[] { 	
				BANKTYPE_CMBC    //民生银行
		};   
	}
	
	public static void main(String[] args) {
		String regex = "1[0-5].*";
		System.out.println(Pattern.matches(regex, "10asd"));
		System.out.println(Pattern.matches(regex, "11asd"));
		System.out.println(Pattern.matches(regex, "12asd"));
		System.out.println(Pattern.matches(regex, "13asd"));
		System.out.println(Pattern.matches(regex, "14asd"));
		System.out.println(Pattern.matches(regex, "15asd"));
		System.out.println(Pattern.matches(regex, "00asd"));
		System.out.println(Pattern.matches(regex, "160asd"));
		System.out.println(Pattern.matches(regex, "10"));
	}
	/**
	 * 根据授权码(被扫付款码)获取行别
	 * @param authCode
	 * @return
	 * @author	马颂超
	 * @date	2017年5月10日
	 */
	public static BankTypeEnum getBankTypeByAuthCode(String authCode){
		if (authCode!=null) {
			if (Pattern.matches("1[0-5].*", authCode)) {//以10-15开头的
				return BankTypeEnum.WECHAT;
			}else if (authCode.startsWith("28")) {
				return BankTypeEnum.ALIPAY;
			}else if (authCode.startsWith("62")) {
				return BankTypeEnum.UNION_PAY;
//			}else if (authCode.startsWith("31")) {
//				return BankType.BAIDU;//百度钱包
			}else if(authCode.startsWith("87")){
				return BankTypeEnum.BANKTYPE_ZLZF;
			}
		}
		return null;
	}
	/**
	 * pcpos展示银行类型
	 * @return
	 */
	public static BankTypeEnum [] getAggregateBankTypeList(){
		return new BankTypeEnum[]{BankTypeEnum.WECHAT,BankTypeEnum.ALIPAY,BankTypeEnum.UNION_PAY};
	}
	
	/**
	 * 存管户账户子行别
	 * @return
	 */
	public static BankTypeEnum [] getDepositBankTypeList(){
		return new BankTypeEnum[]{BankTypeEnum.UNION_PAY,BankTypeEnum.EPCC};
	}
	
	/**
	* @Description: 跨境入账行别  
	* @return BankTypeEnum[]
	* @author: libingtan
	* @date: 2020年8月20日 下午3:47:55 
	*/ 
	public static BankTypeEnum [] getCbpsBankTypeList(){
		return new BankTypeEnum[]{BankTypeEnum.BANKTYPE_BC,BankTypeEnum.EPCC};
	}
	
	/**
	 * 
	* @Title: transformBankCodeToBankType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param bankCode
	* @param @return    设定文件 
	* @return BankType    返回类型 
	* @throws
	*/
	public static BankTypeEnum transformBankCodeToBankType(String bankCode){
		if(StringUtils.isBlank(bankCode)) {
			return BANKTYPE_OTHER;
		}
		BankTypeEnum bankreturn = parseOfFinanceCode(bankCode);
		if(bankreturn == null) {
			if (12 ==bankCode.length() ) {
				String prefix = bankCode.substring(0, 3);
				bankreturn = cnaps2banktypemap.get(prefix);
			}
		}
		if(null == bankreturn) {
			bankreturn = BANKTYPE_OTHER;
		}
		return bankreturn;
	}
	/**
	* @Descriptio备付金监管银行
	* @return
	* @return BankType[]
	* @throws  
	* @author: xxm
	* @date: 2020年8月26日 下午2:49:48
	 */
	public static BankTypeEnum [] getSupervisionBankList(){
		return new BankTypeEnum[]{
				BankTypeEnum.EPCC,
				BankTypeEnum.UNION_PAY
				};
	}
	
	/**
	 * 
	 * @Description: 跨境人民币付款功能再改造 - 登记合作银行来账 (中国银行)
	 * @return
	 * BankType[]
	 * @author: liushuang
	 * @date:2020年10月23日 上午10:41:55
	 */
	public static BankTypeEnum[] cbpsbankdeposit() {
		Set<BankTypeEnum> set = new LinkedHashSet<BankTypeEnum>();
		set.add(BANKTYPE_BC);
		set.add(UNION_PAY);
		set.add(EPCC);
		BankTypeEnum[] types = (BankTypeEnum[]) set.toArray(new BankTypeEnum[set.size()]);
		return types;
	}
}
