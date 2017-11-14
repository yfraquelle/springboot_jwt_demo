package cn.edu.nju.dzy.domain;

public class PoConstant {

	public static class UserInfoConst {
		public static final int STATUS_REG=0;
		public static final int STATUS_ACTIVE=1;
		public static final int STATUS_DEMO=10;
		public static final int STATUS_BIND=30;
		public static final int STATUS_VIP=35;
		public static final int STATUS_UNBIND=40;
		public static final int STATUS_DISABLE=-1;
		public static final String SYSADMIN = "sysadmin";
		public static final String COMPANYADMIN = "admin";
	}
	

	public static class WbsGroupConst {
		public static final int  LEVEL_METRIC = 4;
		public static final int  LEVEL_DEVICE = 3;
		public static final int  LEVEL_UNIT = 2;
		public static final int  LEVEL_SYSTEM = 2;
		public static final int  LEVEL_EQUIP = 1;
		public static final int  LEVEL_FACTORY = 0;
		
		public static final int  GTYPE_METRIC = 110;
		public static final int  GTYPE_DEVICE = 120;
		public static final int  GTYPE_UNIT = 130;
		public static final int  GTYPE_SYSTEM = 140;
		public static final int  GTYPE_EQUIP = 150;
		public static final int  GTYPE_FACTORY = 160;
		
	//110参数、120设备、130单元、140系统、150装置、160工厂
	}
	
	public static class ObsGroupConst {
		//Org：210 公司、220 厂、230 车间
		public static final int LEVEL_COMPANY=1;//公司
		public static final int LEVEL_FACTORY=2;//工厂
		public static final int LEVEL_WORKSHOP=3;//车间
		
		public static final int GTYPE_COMPANY=210;
		public static final int GTYPE_FACTORY=220;
		public static final int GTYPE_WORKSHOP=230;
	
	}

	public static class ChemModelConst {
		
		//0:Signle Simple, 1: Single R ,2 Multiple R Model, 3: Trainning only
		
		public static final int MODEL_TYPE_SINGLE=0;
		public static final int MODEL_TYPE_SINGLE_R=1;
		public static final int MODEL_TYPE_MULTIPLE_ASSERT=2;
		public static final int MODEL_TYPE_MULTIPLE_TRAIN=3;
	}
	
	public static class ChemMetricConst {
		
		//1 device ，2 unit , 3 multiple with model ,4 single with valv, 5 single with model,6:for trainning only
		public static final int MTYPE_DEVICE=1;
		public static final int MTYPE_UNIT=2;
		public static final int MTYPE_MULT_PC=3;
		public static final int MTYPE_VALV=4;
		public static final int MTYPE_SPC_MODEL=5;
		public static final int MTYPE_TRAINING_MODEL=6;
		
		
		 //10 过速、 20 过幅、 30 中断 //40：报警
		public static final int ALARM_SPEED =10;
		public static final int ALARM_AMP =20;
		public static final int ALARM_INTERUPT =30;
		public static final int ALARM_ALERT =40;
		
		public static final int ATTR_MUTILPLE=100; 
		//1 浓度
		//2 藏量
		//3 阀位
		//4 界位
		//5 报警
		//6 累计流量
		//7 料位
		//8 流量
		//9 流速
		//10 密度
		//11 浓度
		//12 投用状态
		//13 温度
		//14 压差
		//15 压力
		//16 液位
		//17 含油量
		//18 质量

		//100复合
	}
	
	public static class ParameterConst {
	
		// 0: 上阈值， 1:下阈值 ， 2: 上比例， 3:下比例
		
		
		public static final int TYPE_HIGH_LIMIT=0;
		public static final int TYPE_LOW_LIMIT=1;
		
		public static final int TYPE_HIGH_FACTOR=2;
		
		public static final int TYPE_LOW_FACTOR=3;
	}
	
	public static class AlarmHistoryConst {
		//0 open, 1:Sent, 2:read,3:action, 4:closed,5:Ignore
		public static final int STATUS_OPEN=0;
		public static final int STATUS_SENT=1;
		public static final int STATUS_READ=2;
		public static final int STATUS_ACT=3;
		public static final int STATUS_CLOSED=4;
		public static final int STATUS_IGNORE=5;
		
		
		
	}
	
	public static class Alarmv2Const{
		public static final String ALARM_STATUS_OPEN="alarm_status_open";//当前值仍处于报警状态
		public static final String ALARM_STATUS_CLOSE="alarm_status_close";//当前值已不在报警状态
		
		public static final String USER_STATUS_NOTSEND="user_status_notsend";//消息未发送
		public static final String USER_STATUS_SENDED="user_status_sended";//消息已发送
		public static final String USER_STATUS_READED="user_status_readed";//消息已被读取
		public static final String USER_STATUS_CLOSE="user_status_close";//用户已关闭此报警
		public static final String USER_STATUS_IGNOR="user_status_ignor";//用户忽略此报警
	}
	
}
