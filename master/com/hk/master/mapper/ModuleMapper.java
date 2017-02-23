package com.hk.master.mapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import com.hk.base.entity.Module;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class ModuleMapper {

	public static Module moduleMapper(HttpServletRequest request)throws IOException {
		Module Module = new Module();
		try {
			String jsonBody = IOUtils.toString(request.getInputStream());
			Module = new ObjectMapper().readValue(jsonBody, Module.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Module;
	}

}
