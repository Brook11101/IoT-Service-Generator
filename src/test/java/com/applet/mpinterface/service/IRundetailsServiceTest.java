package com.applet.mpinterface.service;

import com.applet.mpinterface.framework.SpringGenerator;

import com.applet.mpinterface.framework.ConsumerGenerator;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IRundetailsServiceTest {
    @Autowired
    private IRundetailsService rundetailsService;

    @Autowired
    private SpringGenerator generator;
    @Autowired
    private ConsumerGenerator consumerGenerator;
    @Autowired
    private ISpecificGenerator specificGenerator;

    @Test
    public void generateAppletSpring() throws TemplateException, IOException {
        //生成到有的框架中
        generator.generateSpring("D:\\preStage\\IoTRPC\\appletA", "appletA", 9090);//appletA生成时用的
        generator.generateSpring("D:\\preStage\\IoTRPC\\appletB", "appletB", 9091);

    }

    @Test
    public void generateAppletFunction() {
        //改成生成到现有目录下面
        specificGenerator.generateAllDetailgetterCode("dWpBihQN", 1702953840, "D:\\preStage\\IoTRPC\\appletA" + "/src/main/java/com/appletA/mpinterface", "appletA");//appletA生成时用的
        specificGenerator.generateAllDetailgetterCode("D65dGVxZ", 1702964040, "D:\\preStage\\IoTRPC\\appletB" + "/src/main/java/com/appletB/mpinterface", "appletB");
    }


    @Test
    public void generateConsumer() throws TemplateException, IOException {
        //生成到有的框架中
        consumerGenerator.generateSpring("D:\\preStage\\IoTRPC\\ConsumerA", "ConsumerA", 9092);

        String javaCode = "@RemoteCallReference\n" +
                "private IappletAService appletAService;\n" +
                "\n" +
                "@GetMapping(\"SwitchName\")\n" +
                "public String SwitchName() {\n" +
                "return appletAService.SwitchName(\"dWpBihQN\", 1702953840);\n" +
                "}";

        consumerGenerator.generateController("D:\\preStage\\IoTRPC\\ConsumerA", javaCode, "ConsumerA");
    }

    // 实验次数
    private int appletNum = 1;

    @Test
    public void generateAppletExperiment() throws IOException, TemplateException, InterruptedException {
        runExperiment(appletNum);
    }

    public void runExperiment(int appletNum) throws IOException, TemplateException, InterruptedException {
        // 用来记录 10 次实验的【总生成时间】、【总启动时间】、【总下线时间】
        // 每个 list 里会保存 10 个数值，对应 10 次实验
        List<Long> generateTimes = new ArrayList<>();
        List<Long> startTimes = new ArrayList<>();
        List<Long> shutdownTimes = new ArrayList<>();

        // 做 10 次实验
        for (int j = 1; j <= 1; j++) {

            // -------------------- 生成阶段 --------------------
            long generateStartTime = System.currentTimeMillis();

            // 生成 appletNum 个微服务
            for (int i = 1; i <= appletNum; i++) {
                String appletName = "applet" + i;   // 这里 i 表示第 i 个微服务
                int port = 13000 + i;

                // 生成微服务
                generator.generateSpring("D:\\preStage\\IoTRPC\\" + appletName, appletName, port);
                specificGenerator.generateAllDetailgetterCode("dWpBihQN", 1702953840,
                        "D:\\preStage\\IoTRPC\\" + appletName + "\\src\\main\\java\\com\\" + appletName + "\\mpinterface",
                        appletName);
            }

            long generateEndTime = System.currentTimeMillis();
            long generateTotalTime = generateEndTime - generateStartTime; // 该轮生成所有微服务所耗费的时间
            generateTimes.add(generateTotalTime);


            // -------------------- 启动阶段 --------------------
            long startupStartTime = System.currentTimeMillis();

            // 针对本轮生成的 appletNum 个微服务，分别编译、打包并启动
            for (int i = 1; i <= appletNum; i++) {
                String appletName = "applet" + i;
                compileAndPackageAndRunJar(appletName, 13000 + i);
            }

            long startupEndTime = System.currentTimeMillis();
            long startupTotalTime = startupEndTime - startupStartTime; // 该轮启动所有微服务所耗费的时间
            startTimes.add(startupTotalTime);


            // -------------------- 下线阶段 --------------------
            long shutdownStartTime = System.currentTimeMillis();

            // 下线本轮启动的 appletNum 个微服务
            for (int i = 1; i <= appletNum; i++) {
                String appletName = "applet" + i;
                shutdownService("127.0.0.1", 13000 + i);

                String batFilePath = "D:\\preStage\\IoTRPC\\" + appletName + "\\kill-" + appletName + ".bat"; // 批处理文件路径
                generateKillBatchFile(batFilePath, appletName);

                try {
                    // 使用 "cmd.exe /c start" 来启动 bat 文件
                    String[] cmd = {"C:\\Windows\\System32\\cmd.exe", "/c", "start", "", "/b", batFilePath};
                    Runtime.getRuntime().exec(cmd);
                    System.out.println("通过 cmd.exe /c kill 删除jar进程。");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            long shutdownEndTime = System.currentTimeMillis();
            long shutdownTotalTime = shutdownEndTime - shutdownStartTime; // 该轮下线所有微服务所耗费的时间
            shutdownTimes.add(shutdownTotalTime);
            //用于java进程的全部关闭


            System.out.println("===== Experiment " + j + " =====");
            System.out.println("Generate total time: " + generateTotalTime + " ms");
            System.out.println("Startup total time:  " + startupTotalTime + " ms");
            System.out.println("Shutdown total time: " + shutdownTotalTime + " ms");
            System.out.println("=====================");
        }

        // 现在，10 次实验都完成了，分别对 generateTimes, startTimes, shutdownTimes 进行统计
        printStatistics("Generate times", generateTimes);
        printStatistics("Startup times", startTimes);
        printStatistics("Shutdown times", shutdownTimes);
    }

    // 执行 Maven 构建并打包 + 启动 JAR 文件并计算启动时间
    public long compileAndPackageAndRunJar(String appletName, int port) throws IOException {
        // 执行 Maven 编译和打包
        ProcessBuilder processBuilder = new ProcessBuilder("D:\\apache-maven-3.9.0\\bin\\mvn.cmd", "clean", "install");
        processBuilder.directory(new File("D:\\preStage\\IoTRPC\\" + appletName)); // 设置项目目录

        long startTime = System.currentTimeMillis();
        Process process = processBuilder.start();
        try {
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Maven build and package successful for " + appletName);
            } else {
                System.err.println("Maven build failed with exit code " + exitCode + " for " + appletName);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 调用批处理文件启动 JAR 文件
        String batFilePath = "D:\\preStage\\IoTRPC\\" + appletName + "\\start-" + appletName + ".bat"; // 批处理文件路径
        generateBatchFile(batFilePath, appletName);

        try {
            // 使用 "cmd.exe /c start" 来启动 bat 文件
            String[] cmd = {"C:\\Windows\\System32\\cmd.exe", "/c", "start", "", "/b", batFilePath};
            Runtime.getRuntime().exec(cmd);
            System.out.println("通过 cmd.exe /c start 启动批处理文件，模拟桌面点击。");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 检查健康检查端点是否返回正常状态
        boolean isStarted = false;
        while (!isStarted) {
            isStarted = isServiceHealthy("127.0.0.1", port);
            System.out.println(isStarted);
        }
        System.out.println("Java process start successful " + appletName);

        return System.currentTimeMillis() - startTime; // 返回总的启动时间，包括编译打包和启动
    }

    // 通过 HTTP 请求检测健康检查端点来判断服务是否启动
    public boolean isServiceHealthy(String host, int port) {
        try {
            // 构建健康检查 URL
            URL url = new URL("http://" + host + ":" + port + "/actuator/health");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);  // 设置连接超时为 2 秒
            connection.setReadTimeout(2000);     // 设置读取超时为 2 秒

            // 获取响应码
            int responseCode = connection.getResponseCode();
            return responseCode == 200;  // 如果返回 200 OK，表示服务健康，启动
        } catch (IOException e) {
            return false;  // 如果发生异常，表示服务未启动或不可达
        }
    }

    // 生成批处理文件的内容
    private void generateBatchFile(String batFilePath, String appletName) throws IOException {
        File batFile = new File(batFilePath);
        // 创建批处理文件
        try (FileWriter writer = new FileWriter(batFile)) {
            // 写入启动命令
            String command = "D:\\Java8\\bin\\java.exe -jar D:\\preStage\\IoTRPC\\" + appletName + "\\target\\" + appletName + "-0.0.1-SNAPSHOT.jar";
            writer.write(command);
        }
    }

    private void generateKillBatchFile(String batFilePath, String appletName) throws IOException {
        File batFile = new File(batFilePath);
        // 构造 jar 文件名
        String jarName = appletName + "-0.0.1-SNAPSHOT.jar";

        // 构造批处理脚本内容：
        // 1. 关闭回显
        // 2. 使用 for 循环遍历 jps -l 的输出，找到包含 jarName 的行，并提取 PID（批处理脚本中循环变量需用 %%i）
        // 3. 使用 taskkill 命令终止对应的进程
        String command = "@echo off\r\n"
                + "for /f \"tokens=1\" %%i in ('jps -l ^| find \"" + jarName + "\"') do taskkill /F /PID %%i\r\n";

        try (FileWriter writer = new FileWriter(batFile)) {
            writer.write(command);
        }
    }


    public void shutdownService(String host, int port) {
        String shutdownUrl = "http://" + host + ":" + port + "/actuator/shutdown";
        try {
            URL url = new URL(shutdownUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true); // 允许发送请求体（即使没有内容也需要设置）

            // 发送空的请求体
            try (OutputStream os = connection.getOutputStream()) {
                os.write(new byte[0]);
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Shutdown response code: " + responseCode);
            if (responseCode == 200) {
                System.out.println("Service shutdown successfully.");
            } else {
                System.out.println("Failed to shutdown service. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws IOException {
        String batFilePath = "D:\\preStage\\IoTRPC\\" + "applet1" + "\\kill-" + "applet1" + ".bat"; // 批处理文件路径
        generateKillBatchFile(batFilePath, "applet1");

        try {
            // 使用 "cmd.exe /c start" 来启动 bat 文件
            String[] cmd = {"C:\\Windows\\System32\\cmd.exe", "/c", "start", "", "/b", batFilePath};
            Runtime.getRuntime().exec(cmd);
            System.out.println("通过 cmd.exe /c kill 删除jar进程。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 打印统计数据：最小时间，最大时间，平均时间
    public void printStatistics(String label, List<Long> times) {
        long minTime = times.stream().mapToLong(Long::longValue).min().orElse(0);
        long maxTime = times.stream().mapToLong(Long::longValue).max().orElse(0);
        double avgTime = times.stream().mapToLong(Long::longValue).average().orElse(0);

        System.out.println(label + " - Minimum time: " + minTime + " ms");
        System.out.println(label + " - Maximum time: " + maxTime + " ms");
        System.out.println(label + " - Average time: " + avgTime + " ms");
    }


}