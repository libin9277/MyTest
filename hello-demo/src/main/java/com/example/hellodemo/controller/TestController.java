package com.example.hellodemo.controller;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author libin
 * @date 2021年12月22日 10:55
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("down")
    private void testPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!getLicense()) {
            return;
        }
        long start = System.currentTimeMillis();
        ClassPathResource classPathResource = new ClassPathResource("applyLetter.docx");
        InputStream inputStream = classPathResource.getInputStream();

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("seq", i + 1);
            map.put("xinxitigongfang", "北京");
            map.put("contextType", "测试");
            list.add(map);
        }
//        Map map = new LinkedHashMap();
//        map.put("seq",1);
//        map.put("xinxitigongfang","北京");
//        map.put("contextType","测试");
//        Map map2 = new LinkedHashMap();
//        map.put("seq",2);
//        map.put("xinxitigongfang","湖南");
//        map.put("contextType","测试");
//        list.add(map);
//        list.add(map2);
        System.out.println(list);
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();

        Configure config = Configure.builder()
                .bind("goods", policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputStream, config).render(
                new HashMap<String, Object>() {{
                    put("unitName1", "Hi, poi-tl Word模板引擎");
                    put("goods", list);
                }});

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        template.writeAndClose(byteArrayOutputStream);
        byteArrayOutputStream.close();
        //所需要转为PDF的Word文档
        InputStream stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Document convertDoc = new Document(stream);

        ByteArrayOutputStream rntStream = new ByteArrayOutputStream();
        convertDoc.save(rntStream, SaveFormat.PDF);
        stream.close();

        response.setContentType("application/pdf;charset=utf-8");
        OutputStream out = response.getOutputStream();
        out.write(rntStream.toByteArray());

        rntStream.close();
        out.flush();
        out.close();
        long end = System.currentTimeMillis();
        System.out.println("用时" + (end - start) + "毫秒");
    }

    private static boolean getLicense() {
        boolean result = false;
        try {
            // 凭证
            String license =
                    "<License>\n" +
                            "  <Data>\n" +
                            "    <Products>\n" +
                            "      <Product>Aspose.Total for Java</Product>\n" +
                            "      <Product>Aspose.Words for Java</Product>\n" +
                            "    </Products>\n" +
                            "    <EditionType>Enterprise</EditionType>\n" +
                            "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
                            "    <LicenseExpiry>20991231</LicenseExpiry>\n" +
                            "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
                            "  </Data>\n" +
                            "  <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n" +
                            "</License>";
            InputStream is = new ByteArrayInputStream(license.getBytes("UTF-8"));
            License asposeLic = new License();
            asposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "file is empty";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 文件存储路径
        String filePath = "D:/data/" + UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
        logger.info("save file to:" + filePath);
        File dest = new File(filePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
