package cn.edu.nju.dzy.common;

import cn.edu.nju.dzy.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PiSettings {
	public static final Logger log = LoggerFactory.getLogger(PiSettings.class);

	
	private JHipsterProperties jHipsterProperties;
	
	private static PiSettings settings = new PiSettings();
	
	private List<String> wxAdmin=null;
	
	private PiSettings(){
		int ret;
		if (getjHipsterProperties()!=null){
			log.info("!!!!!!!!jh not null");
			ret= getjHipsterProperties().getUtcloud().getWxEnv();
			WX_ENV=ret;
		}
		else{
			log.info("!!!!!!jh is null");
			WX_ENV=0;
		}
		log.info("!!!!!!!!!!Use WX_ENV="+WX_ENV+".0-PRD 2-DEV 3-UT");
		
	}
	
	public String getConfKey(){
		if (getjHipsterProperties()!=null){
			return getjHipsterProperties().getUtcloud().getConfKey();
		}
		return "menuq1w2e3r4";
	}
	
	public static PiSettings instance(){
		return settings;
	}
	
	
	
	private  int WX_ENV=3; // 0 product , 2 dev, 3- sit

	public boolean isOpenToAll;
	
	public int getWxEnv(){
		int ret;
		if (getjHipsterProperties()!=null){
			ret= getjHipsterProperties().getUtcloud().getWxEnv();
			WX_ENV=ret;
		}
		else
		{
			ret= WX_ENV;
		}
		return ret;
	}
	
	public boolean isDebug(){
		if (getjHipsterProperties()!=null){
			return getjHipsterProperties().getUtcloud().isDebug();
		}
		return true;
	}


	public String getDefaultLang() {
		if (getjHipsterProperties()!=null){
			return getjHipsterProperties().getUtcloud().getLang();
		}
		return "zh_CN";
	}

	public String getBaseUrl(){
		if (getjHipsterProperties()!=null){
			return getjHipsterProperties().getUtcloud().getUrl();
		}
	
		return "http://wx.ut-cloud.net/wx30";
	}

	
	public boolean isRedis(){
		if (getjHipsterProperties()!=null){
			return getjHipsterProperties().getUtcloud().isRedis();
		}
	
		return false; 
	}

	public JHipsterProperties getjHipsterProperties() {
		return jHipsterProperties;
	}

	public void setjHipsterProperties(JHipsterProperties jHipsterProperties) {
		log.info("!!!!!!!cqtest set jh");
		this.jHipsterProperties = jHipsterProperties;
	}
}
