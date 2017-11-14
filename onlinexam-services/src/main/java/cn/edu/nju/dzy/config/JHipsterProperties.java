package cn.edu.nju.dzy.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Properties specific to JHipster.
 *
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "jhipster", ignoreUnknownFields = false)
public class JHipsterProperties {

	private final Async async = new Async();

	private final Http http = new Http();

	private final Cache cache = new Cache();

	private final Mail mail = new Mail();

	private final Security security = new Security();

	private final Swagger swagger = new Swagger();

	private final Metrics metrics = new Metrics();

	private final CorsConfiguration cors = new CorsConfiguration();

	private final Social social = new Social();

	private final Ribbon ribbon = new Ribbon();

	private final OpenTsdb opentsdb = new OpenTsdb();

	private final Utcloud utcloud = new Utcloud();
	
	public OpenTsdb getOpentsdb() {
		return opentsdb;
	}

	public Async getAsync() {
		return async;
	}

	public Http getHttp() {
		return http;
	}

	public Cache getCache() {
		return cache;
	}

	public Mail getMail() {
		return mail;
	}

	public Security getSecurity() {
		return security;
	}

	public Swagger getSwagger() {
		return swagger;
	}

	public Metrics getMetrics() {
		return metrics;
	}

	public CorsConfiguration getCors() {
		return cors;
	}

	public Social getSocial() {
		return social;
	}

	public Ribbon getRibbon() {
		return ribbon;
	}

	public static class Async {

		private int corePoolSize = 2;

		private int maxPoolSize = 50;

		private int queueCapacity = 10000;

		public int getCorePoolSize() {
			return corePoolSize;
		}

		public void setCorePoolSize(int corePoolSize) {
			this.corePoolSize = corePoolSize;
		}

		public int getMaxPoolSize() {
			return maxPoolSize;
		}

		public void setMaxPoolSize(int maxPoolSize) {
			this.maxPoolSize = maxPoolSize;
		}

		public int getQueueCapacity() {
			return queueCapacity;
		}

		public void setQueueCapacity(int queueCapacity) {
			this.queueCapacity = queueCapacity;
		}
	}

	public static class Http {

		private final Cache cache = new Cache();

		public Cache getCache() {
			return cache;
		}

		public static class Cache {

			private int timeToLiveInDays = 1461;

			public int getTimeToLiveInDays() {
				return timeToLiveInDays;
			}

			public void setTimeToLiveInDays(int timeToLiveInDays) {
				this.timeToLiveInDays = timeToLiveInDays;
			}
		}
	}

	public static class Cache {

		private int timeToLiveSeconds = 3600;

		private final Ehcache ehcache = new Ehcache();

		public int getTimeToLiveSeconds() {
			return timeToLiveSeconds;
		}

		public void setTimeToLiveSeconds(int timeToLiveSeconds) {
			this.timeToLiveSeconds = timeToLiveSeconds;
		}

		public Ehcache getEhcache() {
			return ehcache;
		}

		public static class Ehcache {

			private String maxBytesLocalHeap = "16M";

			public String getMaxBytesLocalHeap() {
				return maxBytesLocalHeap;
			}

			public void setMaxBytesLocalHeap(String maxBytesLocalHeap) {
				this.maxBytesLocalHeap = maxBytesLocalHeap;
			}
		}
	}

	public static class Mail {

		private String from = "admin@chemcyber.com";

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}
	}

	public static class Security {

		private final Authentication authentication = new Authentication();

		public Authentication getAuthentication() {
			return authentication;
		}

		public static class Authentication {

			private final Jwt jwt = new Jwt();

			public Jwt getJwt() {
				return jwt;
			}

			public static class Jwt {

				private String secret;

				private long tokenValidityInSeconds = 1800;
				private long tokenValidityInSecondsForRememberMe = 2592000;

				public String getSecret() {
					return secret;
				}

				public void setSecret(String secret) {
					this.secret = secret;
				}

				public long getTokenValidityInSeconds() {
					return tokenValidityInSeconds;
				}

				public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
					this.tokenValidityInSeconds = tokenValidityInSeconds;
				}

				public long getTokenValidityInSecondsForRememberMe() {
					return tokenValidityInSecondsForRememberMe;
				}

				public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
					this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
				}
			}
		}
	}

	public static class Swagger {

		private String title = "iChem API";

		private String description = "iChem API documentation";

		private String version = "0.0.1";

		private String termsOfServiceUrl;

		private String contactName;

		private String contactUrl;

		private String contactEmail;

		private String license;

		private String licenseUrl;

		private Boolean enabled;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getTermsOfServiceUrl() {
			return termsOfServiceUrl;
		}

		public void setTermsOfServiceUrl(String termsOfServiceUrl) {
			this.termsOfServiceUrl = termsOfServiceUrl;
		}

		public String getContactName() {
			return contactName;
		}

		public void setContactName(String contactName) {
			this.contactName = contactName;
		}

		public String getContactUrl() {
			return contactUrl;
		}

		public void setContactUrl(String contactUrl) {
			this.contactUrl = contactUrl;
		}

		public String getContactEmail() {
			return contactEmail;
		}

		public void setContactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
		}

		public String getLicense() {
			return license;
		}

		public void setLicense(String license) {
			this.license = license;
		}

		public String getLicenseUrl() {
			return licenseUrl;
		}

		public void setLicenseUrl(String licenseUrl) {
			this.licenseUrl = licenseUrl;
		}

		public Boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}
	}

	public static class Metrics {

		private final Jmx jmx = new Jmx();

		private final Spark spark = new Spark();

		private final Graphite graphite = new Graphite();

		private final Logs logs = new Logs();

		public Jmx getJmx() {
			return jmx;
		}

		public Spark getSpark() {
			return spark;
		}

		public Graphite getGraphite() {
			return graphite;
		}

		public Logs getLogs() {
			return logs;
		}

		public static class Jmx {

			private boolean enabled = true;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}
		}

		public static class Spark {

			private boolean enabled = false;

			private String host = "localhost";

			private int port = 9999;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getHost() {
				return host;
			}

			public void setHost(String host) {
				this.host = host;
			}

			public int getPort() {
				return port;
			}

			public void setPort(int port) {
				this.port = port;
			}
		}

		public static class Graphite {

			private boolean enabled = false;

			private String host = "localhost";

			private int port = 2003;

			private String prefix = "iChem";

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getHost() {
				return host;
			}

			public void setHost(String host) {
				this.host = host;
			}

			public int getPort() {
				return port;
			}

			public void setPort(int port) {
				this.port = port;
			}

			public String getPrefix() {
				return prefix;
			}

			public void setPrefix(String prefix) {
				this.prefix = prefix;
			}
		}

		public static class Logs {

			private boolean enabled = false;

			private long reportFrequency = 60;

			public long getReportFrequency() {
				return reportFrequency;
			}

			public void setReportFrequency(int reportFrequency) {
				this.reportFrequency = reportFrequency;
			}

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}
		}
	}

	private final Logging logging = new Logging();

	public Logging getLogging() {
		return logging;
	}

	public Utcloud getUtcloud() {
		return utcloud;
	}

	public static class Logging {

		private final Logstash logstash = new Logstash();

		public Logstash getLogstash() {
			return logstash;
		}

		public static class Logstash {

			private boolean enabled = false;

			private String host = "localhost";

			private int port = 5000;

			private int queueSize = 512;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getHost() {
				return host;
			}

			public void setHost(String host) {
				this.host = host;
			}

			public int getPort() {
				return port;
			}

			public void setPort(int port) {
				this.port = port;
			}

			public int getQueueSize() {
				return queueSize;
			}

			public void setQueueSize(int queueSize) {
				this.queueSize = queueSize;
			}
		}
	}

	public static class Social {

		private String redirectAfterSignIn = "/#/home";

		public String getRedirectAfterSignIn() {
			return redirectAfterSignIn;
		}

		public void setRedirectAfterSignIn(String redirectAfterSignIn) {
			this.redirectAfterSignIn = redirectAfterSignIn;
		}
	}

	public static class Ribbon {

		private String[] displayOnActiveProfiles;

		public String[] getDisplayOnActiveProfiles() {
			return displayOnActiveProfiles;
		}

		public void setDisplayOnActiveProfiles(String[] displayOnActiveProfiles) {
			this.displayOnActiveProfiles = displayOnActiveProfiles;
		}
	}

	public static class OpenTsdb {

		private String host = "localhost";

		private int port = 4242;

		private int response = 0;

		public int getResponse() {
			return response;
		}

		public void setResponse(int response) {
			this.response = response;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

	}
	
	public static class Utcloud {
		public final static  int WX_PRD=0;
		public final static  int WX_DEV=2;
		public final static  int WX_UT=3; // 0 product , 2 dev, 3- sit
		public final static  int WX_STAGE=4;
		private int wxEnv = WX_DEV;
		private String confKey="menuq1w2e3r4";
		private boolean debug=false;
		private String lang="zh_CN";
		private String url="http://wx.ut-cloud.net/wx30";
		private boolean redis =true;

		public int getWxEnv() {
			return wxEnv;
		}

		public void setWxEnv(int wxEnv) {
			this.wxEnv = wxEnv;
		}

		public String getConfKey() {
			return confKey;
		}

		public void setConfKey(String confKey) {
			this.confKey = confKey;
		}

		public boolean isDebug() {
			return debug;
		}

		public void setDebug(boolean isDebug) {
			this.debug = isDebug;
		}

		public String getLang() {
			return lang;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public boolean isRedis() {
			return redis ;
		}
		
		public void setRedis(boolean redis) {
			this.redis= redis;
		}

	}

}
