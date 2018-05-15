package util.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.AttributeType;
import util.BeanOperate;
import util.ValidateParameter;

import com.jspsmart.upload.SmartUpload;

@SuppressWarnings("serial")
public abstract class DispatcherServlet extends HttpServlet {
	private static final String PAGES_BASENAME = "Pages" ;
	private static final String MESSAGES_BASENAME = "Messages" ;
	
	private ResourceBundle pagesResource ;
	private ResourceBundle messagesResource ;
	protected HttpServletRequest request ;
	protected HttpServletResponse response ;
	
	private SmartUpload smart = null ;
	
	// 分页操作
	private Integer currentPage = 1 ;
	private Integer lineSize = 5 ;
	private String keyWord ;
	private String column ;
	
	@Override
	public void init() throws ServletException {
		this.pagesResource = ResourceBundle.getBundle(PAGES_BASENAME, Locale.getDefault()) ;
		this.messagesResource = ResourceBundle.getBundle(MESSAGES_BASENAME, Locale.getDefault()) ;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request ;
		this.response = response ;
		String path = this.getPath("errors.page") ;
		String status = request.getRequestURI().substring(
				request.getRequestURI().lastIndexOf("/") + 1) ;
		if (request.getContentType() != null) {
			if (request.getContentType().contains("multipart/form-data")) {
				this.smart = new SmartUpload() ;
				try {
					this.smart.initialize(super.getServletConfig(), request, 
							response);
					this.smart.upload() ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		}
		if (status!=null && status.length()>0) {
			// 在进行参数的处理之前，需要对提交数据进行验证
			if (this.validateRequest(status)) {
				// 处理参数与简单Java类之间的自动赋值
				this.handleRequest() ;
				// 数据准备完毕，开始执行方法
				try {
					Method method = this.getClass().getMethod(status) ;
					path = this.getPath((String) method.invoke(this)) ;
				} catch (Exception e) {
					e.printStackTrace() ;
				}
			}
		}
		request.getRequestDispatcher(path).forward(request,response) ;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	/**
	 * 取得在Pages.properties文件中定义的访问路径
	 * @param key 访问路径的key
	 * @return 配置文件中的路径内容，如果没有返回null
	 */
	public String getPath(String key) {
		return this.pagesResource.getString(key) ;
	}
	
	/**
	 * 取得Messages.properties文件中的配置文字信息
	 * @param key 访问文字信息的key
	 * @param args 所有占位符的内容
	 * @return 配置文件中的内容，并且是组合后的结果，如果没有返回null
	 */
	public String getMsg(String key, String ... args) {
		String note = this.messagesResource.getString(key) ;
		if (args.length>0 || this.getTitle()==null) {
			return MessageFormat.format(note, args) ;
		} else {
			return MessageFormat.format(note, this.getTitle()) ;
		}
	}
	
	private void handleRequest() {
		if (request.getContentType() != null) {
			if (request.getContentType().contains("multipart/form-data")) {
				// 取得全部的请求参数名称，之所以需要名称，主要是确定自动赋值的操作
				Enumeration<String> enu = this.smart.getRequest().getParameterNames() ;
				while (enu.hasMoreElements()) {	// 循环所有的参数名称
					String paramName = enu.nextElement() ;
					if (paramName.contains(".")) {	// 按照简单Java类处理
						// Bean处理
						AttributeType at = new AttributeType(this,paramName) ;
						if (at.getFieldType().contains("[]")) {
							// 按照数组的方式处理
							BeanOperate bo = new BeanOperate(this,paramName, 
									this.smart.getRequest().getParameterValues(paramName)) ;
						} else {
							// 按照字符串的方式处理
							BeanOperate bo = new BeanOperate(this,paramName, 
									this.smart.getRequest().getParameter(paramName)) ;
						}
					}
				}
			} else {
				// 应该使用普通的request对象进行数据的接收操作
				
				// 取得全部的请求参数名称，之所以需要名称，主要是确定自动赋值的操作
				Enumeration<String> enu = request.getParameterNames() ;
				while (enu.hasMoreElements()) {	// 循环所有的参数名称
					String paramName = enu.nextElement() ;
					if (paramName.contains(".")) {	// 按照简单Java类处理
						// Bean处理                           
						AttributeType at = new AttributeType(this,paramName) ;
						if (at.getFieldType().contains("[]")) {
							// 按照数组的方式处理
							BeanOperate bo = new BeanOperate(this,paramName, 
									request.getParameterValues(paramName)) ;
						} else {
							// 按照字符串的方式处理
							BeanOperate bo = new BeanOperate(this,paramName, 
									request.getParameter(paramName)) ;
						}
					}
				}
			}
		} else {	// 数据不是通过表单提交传递的
			Enumeration<String> enu = request.getParameterNames() ;
			while (enu.hasMoreElements()) {	// 循环所有的参数名称
				String paramName = enu.nextElement() ;
				if (paramName.contains(".")) {	// 按照简单Java类处理
					// Bean处理                           
					AttributeType at = new AttributeType(this,paramName) ;
					if (at.getFieldType().contains("[]")) {
						// 按照数组的方式处理
						BeanOperate bo = new BeanOperate(this,paramName, 
								request.getParameterValues(paramName)) ;
					} else {
						// 按照字符串的方式处理
						BeanOperate bo = new BeanOperate(this,paramName, 
								request.getParameter(paramName)) ;
					}
				}
			}
		}
	}
	
	/**
	 * 判断是否有文件上传
	 * @return 没有文件上传返回false
	 */
	public boolean isUpload() {
		try {
			return this.smart.getFiles().getSize() > 0 ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false ;
	}
	
	/**
	 * 取消上传文件个数
	 * @return
	 */
	public int getUploadCount() {
		return this.smart.getFiles().getCount() ;
	}
	
	/**
	 * 创建一个新的文件名称
	 * @return
	 */
	public String createSingleFileName() {
		String fileName = null ;
		if (this.isUpload()) {
			if (this.smart.getFiles().getFile(0).getContentType().contains("image")) {
				fileName = UUID.randomUUID() + "." + 
						this.smart.getFiles().getFile(0).getFileExt() ;
			}
		}
		return fileName ;
	}
	
	public Map<Integer,String> createMultiFileName() {
		Map<Integer,String> all = new HashMap<Integer,String>() ;
		if (this.isUpload()) {
			for (int i=0 ; i<this.smart.getFiles().getCount() ; i++) {
				if (this.smart.getFiles().getFile(i).getContentType()
						.contains("image")) {
					String fileName = UUID.randomUUID() + "." + 
							this.smart.getFiles().getFile(i).getFileExt() ;
					all.put(i, fileName) ;
				}
			}
		}
		return all ;
	}
	
	/**
	 * 负责文件的保存操作
	 * @param index SmartUpload操作索引
	 * @param fileName 文件名称
	 */
	private void saveFile(int index, String fileName) throws Exception {
		String filePath = super.getServletContext().getRealPath(
			this.getUploadDirectory()) + fileName ;
		File file = new File(filePath) ;
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs() ;
		}
		if (this.smart.getFiles().getFile(index).getContentType()
			.contains("image")) {
				this.smart.getFiles().getFile(index).saveAs(filePath) ;
		}
	}
	
	/**
	 * 存放一个文件信息
	 * @param fileName
	 */
	public void upload(String fileName){
		try {
			this.saveFile(0, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void upload(Map<Integer,String> fileName) {
		Iterator<Map.Entry<Integer,String>> iter = fileName.entrySet().iterator() ;
		while (iter.hasNext()) {
			Map.Entry<Integer, String> me = iter.next() ;
			try {
				this.saveFile(me.getKey(), me.getValue()) ;
			} catch (Exception e) {
				e.printStackTrace() ;
			}
		}
	}
	
	/**
	 * 删除文件
	 * @param fileName
	 */
	public void deleteFile(String fileName) {
		String filePath = super.getServletContext().getRealPath(
				this.getUploadDirectory()) + fileName ;
		File file = new File(filePath) ;
		if (file.exists()) {
			file.delete() ;
		}
	}
	
	/**
	 * 批量删除文件
	 * @param all
	 */
	public void deleteFile(Set<String> all){
		Iterator<String> iter = all.iterator() ;
		while (iter.hasNext()) {
			this.deleteFile(iter.next()) ;
		}
	}
	
	public SmartUpload getSmart() {
		return smart ;
	}
	
	/**
	 * 
	 * @param status 操作状态
	 * @return
	 */
	public boolean validateRequest(String status) {
		boolean flag = true ;
		String rule = null ;
		try {
			Field field = this.getClass().getDeclaredField(status + "Validate");
			field.setAccessible(true) ;	// 取消封装
			rule = (String) field.get(this) ;	// 取出验证规则的数据
		} catch (Exception e) {	// 表示数据不存在
			return true ;
		}
		// 针对于给定的内容进行指定格式的验证
		if (rule == null || "".equals(rule)) {	// 没有规则
			flag = true ;
		} else {	// 有规则
			ValidateParameter vp = new ValidateParameter(this,request,
					this.smart,rule,this.messagesResource) ;
			flag = vp.validate() ;	// 进行验证操作
			this.request.setAttribute("errors", vp.getErrors()) ;
		}
		return flag ;
	}

	/**
	 * 进行验证码的验证
	 * @return
	 */
	public boolean checkCode() {
		String code = this.request.getParameter("code") ;
		String rand = (String) request.getSession().getAttribute("rand") ;
		if (code==null || rand==null) {
			return false ;
		}
		return rand.equalsIgnoreCase(code) ;
	}
	
	public void handleSplit() {
		try {
			this.currentPage = Integer.parseInt(this.request.getParameter("cp")) ;
		} catch (Exception e) {}
		try {
			this.lineSize = Integer.parseInt(this.request.getParameter("ls")) ;
		} catch (Exception e) {}
		this.column = request.getParameter("col") ;
		this.keyWord = request.getParameter("kw") ;
		if (this.column == null) {
			this.column = this.getDefaultColumn() ;
		}
		if (this.keyWord == null) {
			this.keyWord = "" ;		// 查询全部
		}
		this.request.setAttribute("currentPage", this.currentPage) ;
		this.request.setAttribute("lineSize", this.lineSize) ;
		this.request.setAttribute("column", this.column) ;
		this.request.setAttribute("keyWord", this.keyWord) ;
		this.request.setAttribute("columnData", this.getColumnData()) ;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public Integer getLineSize() {
		return lineSize;
	}

	public String getColumn() {
		return column;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public void setMsgAndUrl(String msgKey, String urlKey) {
		this.request.setAttribute("msg", this.getMsg(msgKey, this.getTitle())) ;
		this.request.setAttribute("url", this.getPath(urlKey)) ;
	}
	
	/**
	 * 用于操作后提示成功或者失败的操作<br>
	 * 交由子类来实现，可以由子类设置统一的占位符提示信息名称标记
	 * @return 返回不同子类的描述信息
	 */
	public abstract String getTitle() ;
	
	/**
	 * 用于文件上传操作<br>
	 * 取得要进行文件上传保存的目录
	 * @return
	 */
	public abstract String getUploadDirectory() ;
	
	/**
	 * 用于分页操作<br>
	 * 得到分页搜索条的数据列
	 * @return
	 */
	public abstract String getColumnData() ;
	
	/**
	 * 用于分页操作<br>
	 * 得到默认查询的数据列
	 * @return
	 */
	public abstract String getDefaultColumn() ;
	
}
