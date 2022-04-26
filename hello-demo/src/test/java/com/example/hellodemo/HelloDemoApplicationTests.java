package com.example.hellodemo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.data.style.BorderStyle;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.example.hellodemo.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@SpringBootTest
class HelloDemoApplicationTests {

    @Test
    public void testWed(){
        int c = 100;
        int b = 10;
        int d = 1000 % c;
        System.out.println(d);
        for (int e = c; e <= c * b; e = e + c) {
            System.out.println(""+(e - c)+"----------"+e);
        }

        for (int i = 1; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                System.out.print(j+"*"+i+"="+(i*j)+"\t");

            }
            System.out.println();
        }

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
    @Test
    void TestDemo1() throws Exception {
        if (!getLicense()) {
            return;
        }
        long start = System.currentTimeMillis();
        ClassPathResource classPathResource = new ClassPathResource("applyLetter.docx");
        InputStream inputStream =classPathResource.getInputStream();
        System.out.println(classPathResource.getPath());
        List<Map<String, Object>> list = new ArrayList<>(10);
        Map map = new LinkedHashMap();
        map.put("seq",1);
        map.put("xinxitigongfang","北京");
        map.put("contextType","测试");
        list.add(map);

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();

        Configure config = Configure.builder()
                .bind("goods", policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputStream,config).render(
                new HashMap<String, Object>(){{
                    put("unitName1", "Hi, poi-tl Word模板引擎");
                    put("goods", list);
                }});
        //File outFile = File.createTempFile("居民家庭经济状况信息部省联网查询申请", ".docx");
       // FileOutputStream outputStream = new FileOutputStream(outFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        template.writeAndClose(byteArrayOutputStream);
        byteArrayOutputStream.close();
        System.out.println(byteArrayOutputStream.toString());
        //outFile.delete();
        //outputStream.close();
        //获取文档流
//        String fromPath = outFile.getPath();//所需要转为PDF的Word文档
        InputStream stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Document convertDoc = new Document(stream);
        String toPath = "d:1.pdf";//转为PDF的路径
        convertDoc.save(toPath , SaveFormat.PDF);
        stream.close();
//        outFile.delete();
        //saveFormat此处的saveFormat在一下有解释
        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start)+"毫秒");
    }

    @Test
    void testPay() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //Context context = new Context();
        //WxPay wxPay = new WxPay();
        //context.setPay(PayFactory.makePay("zfb"));
        //PayAbstractFactory payAbstractFactory = new PayFactory2();
        //context.setPay(payAbstractFactory.newPay());
        //context.harnldPay();
        PayStrategyFactory factory = PayStrategyFactory.getInstance();
        Pay pay = factory.create(1);
        pay.harnldPay();
    }
    @Test
    void contextLoads() {
        int []a = {3,1,4,2};
        //BubbleSort(a);
       // BubbleSort1(a);
        select_sort(a,4);
    }
    public static void BubbleSort(int [] arr){
        int temp;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = arr.length-1; j > i; j--) {
//                System.out.println(arr[j]);
//                System.out.println(arr[j-1]);
                if(arr[j]<arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void BubbleSort1(int [] arr){

        int temp;//临时变量
        boolean flag;//是否交换的标志
        for(int i=0; i<arr.length-1; i++){   //表示趟数，一共arr.length-1次。
            flag = false;
            for(int j=arr.length-1; j>i; j--){
                System.out.println("j======="+arr[j]);
                System.out.println("j-1======="+arr[j-1]);
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    flag = true;
                }

            }
            for (int n = 0; n < arr.length; n++) {
                System.out.println(arr[n]);
            }

            if(!flag) break;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void select_sort(int array[],int lenth){

        for(int i=0;i<lenth-1;i++){

            int minIndex = i;
            for(int j=i+1;j<lenth;j++){
                if(array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
