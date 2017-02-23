package com.hk.master.mapper;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import com.hk.base.entity.Unit;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class UnitMapper {

	public static Unit unitMapper(HttpServletRequest request)throws IOException {
		Unit unit = new Unit();
		try {
			String jsonBody = IOUtils.toString(request.getInputStream());
			unit = new ObjectMapper().readValue(jsonBody, Unit.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return unit;
	}

}
