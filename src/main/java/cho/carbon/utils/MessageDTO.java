package cho.carbon.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class MessageDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// 当前应用的名称
	private String applicationName;
	
	// bnb 实现的接口
	private Map<String, List<Class<?>>> mapInter = null;
	
	public MessageDTO() {
	}
	
	public MessageDTO(String applicationName, Map<String, List<Class<?>>> mapInter) {
		super();
		this.applicationName = applicationName;
		this.mapInter = mapInter;
	}




	public Map<String, List<Class<?>>> getMapInter() {
		return mapInter;
	}

	public void setMapInter(Map<String, List<Class<?>>> mapInter) {
		this.mapInter = mapInter;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public String toString() {
		return "Message [applicationName=" + applicationName + ", mapInter=" + mapInter + "]";
	}
	
}
