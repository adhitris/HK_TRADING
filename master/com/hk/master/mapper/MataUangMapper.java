package com.hk.master.mapper;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import com.hk.base.entity.MataUang;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class MataUangMapper {

	public static MataUang mataUangMapper(HttpServletRequest request)throws IOException {
		MataUang mataUang = new MataUang();
		try {
			String jsonBody = IOUtils.toString(request.getInputStream());
			mataUang = new ObjectMapper().readValue(jsonBody, MataUang.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mataUang;
	}

}
