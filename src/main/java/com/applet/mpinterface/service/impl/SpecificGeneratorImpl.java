package com.applet.mpinterface.service.impl;

import com.applet.mpinterface.domain.pojo.Rundetails;
import com.applet.mpinterface.mapper.RundetailsMapper;
import com.applet.mpinterface.service.ISpecificGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecificGeneratorImpl extends ServiceImpl<RundetailsMapper, Rundetails> implements ISpecificGenerator {
    @Autowired
    private RundetailsMapper rundetailsMapper;

    @Override
    public void generateAllDetailgetterCode(String id, Integer actionTime, String path, String appletName) {
        // 模板文件路径
        String impltemplatePath = "src/main/templates/detailinfo_impl_template.ftl";
        String servicetemplatePath = "src/main/templates/detailinfo_service_template.ftl";

        //获取到对应条的执行记录，从而获得各个json中的数据
        QueryWrapper<Rundetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("action_time", actionTime);
        // 仅获取最新的一条时queryWrapper换成下面的这个就行
//      queryWrapper.eq("id", id).orderByDesc("action_time").last("LIMIT 1");

        Rundetails rundetails = rundetailsMapper.selectOne(queryWrapper);
        // 使用 Jackson 的 ObjectMapper 来处理 JSON
        ObjectMapper objectMapper = new ObjectMapper();

        //Freemarker生成代码文件的相关
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(RundetailsServiceImpl.class, "/templates");

        // 创建数据模型,数据模型put的list需要五个类别的if都运行完才完
        Map<String, Object> dataModel = new HashMap<>();
        // 创建一个可变的 ArrayList
        List<Map<String, String>> fieldsList = new ArrayList<>();
        try {
            //实际用的应该是下面这个模板
            // 获取模板
            Template impl_template = cfg.getTemplate("detailinfo_impl_template.ftl");
            Template service_template = cfg.getTemplate("detailinfo_service_template.ftl");
            Template rimpl_template = cfg.getTemplate("registry_impl_template.ftl");
            Template rservice_template = cfg.getTemplate("registry_service_template.ftl");
            Template controller_template = cfg.getTemplate("controller_template.ftl");
            if (rundetails.getTriggerNum() != 0) {
                String triggerDetail = rundetails.getTrigger();
                JsonNode jsonNode = objectMapper.readTree(triggerDetail.replaceAll("'", "\"").replaceAll("None", "null"));
                // 如果是数组，你可以使用下面的方式遍历
                if (jsonNode.isArray()) {
                    for (JsonNode element : jsonNode) {
                        // 获取 ingredients 数组
                        JsonNode ingredientsArray = element.get("ingredients");
                        // 遍历数组，提取每个对象的 label 值
                        for (JsonNode ingredient : ingredientsArray) {
                            String label = ingredient.get("label").asText();
                            Map<String, String> field = new HashMap<>();
                            field.put("name", label);
                            field.put("type", "Trigger");
                            fieldsList.add(field);
                            System.out.println("Label: " + label);
                        }

                    }
                }
            }
            if (rundetails.getFilterNum() != 0) {
                String filterDetail = rundetails.getFilter();
                JsonNode jsonNode = objectMapper.readTree(filterDetail.replaceAll("'", "\"").replaceAll("None", "null"));

                // 如果是数组，你可以使用下面的方式遍历
                if (jsonNode.isArray()) {
                    for (JsonNode element : jsonNode) {
                        // 获取 ingredients 数组
                        JsonNode ingredientsArray = element.get("ingredients");
                        // 遍历数组，提取每个对象的 label 值
                        for (JsonNode ingredient : ingredientsArray) {
                            String label = ingredient.get("label").asText();
                            Map<String, String> field = new HashMap<>();
                            field.put("name", label);
                            field.put("type", "Filter");
                            fieldsList.add(field);
                            System.out.println("Label: " + label);
                        }

                    }
                }
            }
            if (rundetails.getDelayNum() != 0) {
                String delayDetail = rundetails.getDelay();
                JsonNode jsonNode = objectMapper.readTree(delayDetail.replaceAll("'", "\"").replaceAll("None", "null"));
                // 如果是数组，你可以使用下面的方式遍历
                if (jsonNode.isArray()) {
                    for (JsonNode element : jsonNode) {
                        // 获取 ingredients 数组
                        JsonNode ingredientsArray = element.get("ingredients");
                        // 遍历数组，提取每个对象的 label 值
                        for (JsonNode ingredient : ingredientsArray) {
                            String label = ingredient.get("label").asText();
                            Map<String, String> field = new HashMap<>();
                            field.put("name", label);
                            field.put("type", "Delay");
                            fieldsList.add(field);
                            System.out.println("Label: " + label);
                        }

                    }
                }
            }
            if (rundetails.getQueryNum() != 0) {
                String queryDetail = rundetails.getQuery();
                JsonNode jsonNode = objectMapper.readTree(queryDetail.replaceAll("'", "\"").replaceAll("None", "null"));

                // 如果是数组，你可以使用下面的方式遍历
                if (jsonNode.isArray()) {
                    for (JsonNode element : jsonNode) {
                        // 获取 fields 数组
                        JsonNode ingredientsArray = element.get("fields");
                        // 遍历数组，提取每个对象的 label 值
                        if (ingredientsArray != null) {
                            for (JsonNode ingredient : ingredientsArray) {
                                String label = ingredient.get("label").asText();
                                Map<String, String> field = new HashMap<>();
                                field.put("name", label);
                                field.put("type", "Query");
                                fieldsList.add(field);
                                System.out.println("Label: " + label);
                            }
                        }
                        // 获取 query_ingredients 数组
                        JsonNode query_ingredientsArray = element.get("query_ingredients");
                        // 遍历数组，提取每个对象的 label 值
                        if (query_ingredientsArray != null) {
                            for (JsonNode outer_ingredient : query_ingredientsArray) {
                                for (JsonNode ingredient : outer_ingredient) {
                                    String label = ingredient.get("label").asText();
                                    Map<String, String> field = new HashMap<>();
                                    field.put("name", label);
                                    field.put("type", "Query");
                                    fieldsList.add(field);

                                    System.out.println("Label: " + label);
                                }
                            }

                        }
                    }
                }
            }
            //action中的数据格式和其他的不一样
            if (rundetails.getActionNum() != 0) {
                String actionDetail = rundetails.getAction();
                JsonNode jsonNode = objectMapper.readTree(actionDetail.replaceAll("'", "\"").replaceAll("None", "null").replaceAll("\"\\{\"", "\"").replaceAll("\"}\"", "\"").replaceAll("t\":\"5", ":").replaceAll("l\":\"H", "l:H").replaceAll("3\",\"d", "3,d"));

                // 如果是数组，你可以使用下面的方式遍历
                if (jsonNode.isArray()) {
                    for (JsonNode element : jsonNode) {
                        // 获取 action_fields 数组
                        JsonNode ingredientsArray = element.get("action_fields");
                        // 遍历数组，提取每个对象的 label 值
                        for (JsonNode ingredient : ingredientsArray) {
                            String label = ingredient.get("label").asText();
                            Map<String, String> field = new HashMap<>();
                            field.put("name", label);
                            field.put("type", "Action");
                            fieldsList.add(field);
                            System.out.println("Label: " + label);
                        }
//                        // 获取 unfiltered_action_fields 数组,好像和上面是重复的
//                        JsonNode unfiltered_action_fields = element.get("action_fields");
//                        // 遍历数组，提取每个对象的 label 值
//                        for (JsonNode ingredient : unfiltered_action_fields) {
//                            String label = ingredient.get("label").asText();
//                            fieldsList.add(Map.of("name", label,"type","Action"));
//                            System.out.println("Label: " + label);
//                        }

                    }
                }
            }

            dataModel.put("fields", fieldsList);
            dataModel.put("appletName", appletName);
            System.out.println(fieldsList);
            // 输出文件路径//需要拼接路径也就是文件名,根据ingredients中具体内容生成所有的detail get的java文件
            //前面半截用变量path替换，可变的生成路径
            String impl_outputPath = path + "/service/impl/" + "SpecificServiceImpl.java";
            String service_outputPath = path + "/service/ISpecificService.java";
            String rimpl_outputPath = path + "/service/impl/" + appletName + "ServiceImpl.java";
            // String rservice_outputPath = path + "/service/I"+appletName+"Service.java";
            //改为放入interface接口中
            String rservice_outputPath = "D:/preStage/IoTRPC/IoT-RPC-Framework/rpc-common-interface/src/main/java/iotruleservice/I" + appletName + "Service.java";
            String controller_outputPath = path + "/controller/SpecificController.java";
            // 创建目录（如果不存在）
            new File(impl_outputPath).getParentFile().mkdirs();
            new File(service_outputPath).getParentFile().mkdirs();
            new File(rimpl_outputPath).getParentFile().mkdirs();
            new File(rservice_outputPath).getParentFile().mkdirs();
            new File(controller_outputPath).getParentFile().mkdirs();
            // 创建 Writer，用于写入生成的 Java 文件
            Writer impl_writer = new FileWriter(impl_outputPath);
            Writer service_writer = new FileWriter(service_outputPath);
            Writer rimpl_writer = new FileWriter(rimpl_outputPath);
            Writer rservice_writer = new FileWriter(rservice_outputPath);
            Writer controller_writer = new FileWriter(controller_outputPath);
            // 填充模板并生成文件
            impl_template.process(dataModel, impl_writer);
            service_template.process(dataModel, service_writer);
            rimpl_template.process(dataModel, rimpl_writer);
            rservice_template.process(dataModel, rservice_writer);
            controller_template.process(dataModel, controller_writer);
            // 关闭 Writer
            impl_writer.close();
            service_writer.close();
            rimpl_writer.close();
            rservice_writer.close();
            System.out.println("Java file generated successfully at: " + impl_outputPath + " and " + service_outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
