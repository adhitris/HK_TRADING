package com.hk.footer.dwr;


import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import com.hk.common.interceptor.BaseInterceptor;

@RemoteProxy(name = "footerDWR")
public class FooterDWR extends BaseInterceptor{
	
	
}
