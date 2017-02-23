package com.hk.setting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.hk.common.controller.AbstractController;
import com.hk.common.exception.ServiceException;
import com.hk.setting.service.CekUserAksesService;



@Controller
@RequestMapping("/akses_user")
public class CekUserAksesController extends AbstractController{
	
	@Autowired
    private CekUserAksesService cekUserAksesService;
	
	@RequestMapping(value = "/is_delete", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isDelete() throws ServiceException {
		return cekUserAksesService.isDelete();
	}
	
	@RequestMapping(value = "/is_create", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isCreate() throws ServiceException {
		return cekUserAksesService.isCreate();
	}
	
	@RequestMapping(value = "/is_update", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isUpdate() throws ServiceException {
		return cekUserAksesService.isUpdate();
	}
	
	@RequestMapping(value = "/is_cancel", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isCancel() throws ServiceException {
		return cekUserAksesService.isCancel();
	}
	
	@RequestMapping(value = "/is_print", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isPrint() throws ServiceException {
		return cekUserAksesService.isPrint();
	}
	
	@RequestMapping(value = "/is_report", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isReport() throws ServiceException {
		return cekUserAksesService.isReport();
	}
	
	@RequestMapping(value = "/is_supervisor", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isSupervisor() throws ServiceException {
		return cekUserAksesService.isSupervisor();
	}
	
	@RequestMapping(value = "/is_superuser", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isSuperuser() throws ServiceException {
		return cekUserAksesService.isSuperuser();
	}
	
	@RequestMapping(value = "/is_confirm", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isConfirm() throws ServiceException {
		return cekUserAksesService.isConfirm();
	}
	
	@RequestMapping(value = "/is_unconfirm", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean isUnconfirm() throws ServiceException {
		return cekUserAksesService.isUnconfirm();
	}
	
	@RequestMapping(value = "/cek_aktif_user", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean cekAktifUser() throws ServiceException {
		return cekUserAksesService.cekUserAktif();
	}
	
	
	


}
