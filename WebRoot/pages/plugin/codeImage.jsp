<%@ page contentType="image/jpeg" pageEncoding="UTF-8"%>
<%@ page import="java.awt.*"%>
<%@ page import="java.awt.image.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.imageio.*"%>
<%
	request.setCharacterEncoding("UTF-8") ;
%>
<%!
	Color getRandColor(int fc, int bc) {	// 给定范围获得随机颜色
		Random random = new Random() ;
		if (fc > 255)	fc=255 ;
		if (bc > 255)	bc=255 ;
		int r=fc+random.nextInt(bc-fc) ;
		int g=fc+random.nextInt(bc-fc) ;
		int b=fc+random.nextInt(bc-fc) ;
		return new Color(r, g, b) ;
	}
%>
<%	// 设置页面不缓存
	response.setHeader("Pragma", "No-cache") ;
	response.setHeader("Cache-Control", "no-cache") ;
	response.setDateHeader("Expires", 0) ;

	// 在内存中创建图像
	// 通过这里可以修改图片的大小
	int width = 80 ;
	int height = 25 ;
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB) ;

	// 获取图形上下文
	// g相当于笔
	Graphics g = image.getGraphics() ;

	// 生成随机类
	Random random = new Random() ;

	// 设定背景色
	g.setColor(getRandColor(200, 250)) ;
	// 画一个实心的长方形，作为背景
	g.fillRect(0, 0, width, height) ;

	// 设定字体
	g.setFont(new Font("宋体", Font.PLAIN, 18)) ;

	// 画边框
	// g.setColor(new Color()) ;
	// g.drawRect(0, 0, width-1, height-1) ;

	// 随机产生155条干扰线，使图像中的验证码不易被其他程序探测到
	g.setColor(getRandColor(160, 200)) ;
	for (int i=0 ; i<155 ; i++) {
		int x = random.nextInt(width) ;
		int y = random.nextInt(height) ;
		int x1 = random.nextInt(12) ;
		int y1 = random.nextInt(12) ;
		g.drawLine(x, y, x+x1, y+y1) ;
	}

	// 取随机产生的验证码（4位数字）
	// String rand = request.getParameter("rand") ;
	// rand = rand.subString(0, rand.indexOf(".")) ;
	String sRand="" ;
	// 如果要使用中文，必须定义字库，可以使用数组进行定义
	// 这里直接写中文会出现乱码，必须将中文转换为Unicode编码
	String [] str = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9"} ;

	for (int i=0 ; i<4 ; i++) {
		String rand = str[random.nextInt(str.length)] ;
		sRand+=rand ;
		// 将验证码显示到图像中
		g.setColor(new Color(20+random.nextInt(110), 20+random.nextInt(110), 20+random.nextInt(110))) ;	// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(rand, 16*i+6, 19) ;
	}

	// 将验证码存入SESSION
	session.setAttribute("rand", sRand) ;

	// 图像生效
	g.dispose() ;

	// 输出图想到页面
	ImageIO.write(image, "JPEG", response.getOutputStream()) ;
	out.clear() ;
	out = pageContext.pushBody() ;
%>