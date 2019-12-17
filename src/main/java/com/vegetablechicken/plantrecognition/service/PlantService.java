package com.vegetablechicken.plantrecognition.service;

import com.vegetablechicken.plantrecognition.Method.Method;
import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.repository.PlantRepository;
import com.vegetablechicken.plantrecognition.response.PlantSearchResponse;
import com.vegetablechicken.plantrecognition.response.ReducePlantsResponse;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PlantService {
    @Resource
    private PlantRepository plantRepository;

    @Autowired
    private HistoryService historyService;

    Random ra = new Random();

    public PlantSearchResponse searchPlant(String email, String name) {
        //do sth
        return null;
    }

    public Plant getPlant(String email, long pid) {
        Plant plant = plantRepository.findByPid(pid);
        historyService.insertHistory(email, plant.getPid(), plant.getName(), plant.getKind(), plant.getPic());//添加到查询历史记录里面
        new Thread(() -> historyService.updateRecommend(email)).start();
        return plant;
    }

    /*public List<ReducePlantsResponse> getPlantByKind(String email,String kind,int count){
        List<Plant> plants=plantRepository.findByKind(kind);
        List<Plant> plants1=new ArrayList<Plant>();
        int amount=plants.size();
        int cnt=0;
        for (int i=0;i<amount&&cnt<count;i++){
            if(ra.nextInt(amount)==0) {
                plants1.add(plants.get(i));
                cnt++;
            }
        }
        return Method.ReducePlant(plants1);
    }*/

    /*public List<ReducePlantsResponse> getRecommendKind(String email,int count){
        List<Plant> plants=plantRepository.findByNameLike();
        List<Plant> plants1=new ArrayList<Plant>();
        int amount=plants.size();
        int cnt=0;
        for (int i=0;i<amount&&cnt<count;i++){
            if(ra.nextInt(amount)==0) {
                plants1.add(plants.get(i));
                cnt++;
            }
        }
        //List<Plant> plants2=new ArrayList<Plant>();
        //for(int i=0;i<plants1.size();i++){
        //    plants2.add(plantRepository.findBykindewithpic(plants2.get(i).getKind()).get(0));
        //}
        return Method.ReducePlant(plants1);
    }*/

    public List<ReducePlantsResponse> getSimplePlantByKind(String email, String kind) {
        List<Plant> plants = plantRepository.findByKind(kind);
        return Method.ReducePlant(plants);
    }

    public List<ReducePlantsResponse> getPlantData(String name) {
        String url = String.format("http://db.kib.ac.cn/CNFlora/SearchEngine.aspx?q=%s", URLEncoder.encode(name));
        List<Plant> plantList = new ArrayList<>();
        Plant plant;
        try {
            Document document = Jsoup.connect(url).get();
            int index = 0;
            for (Element element : document.getElementsByTag("tbody").get(0).children()) {
                if (index > 5)
                    break;
                index ++;
                if (element.children().get(0).text().equals("所在卷")) {
                    continue;
                }
                if (element.children().get(0).text().trim().equals("查无结果")) {
                    break;
                }
                System.out.println(element);
                String plantname = element.children().get(3).text();
                List<Plant> isExist = plantRepository.findByName(plantname);
                if (!isExist.isEmpty()) plant = isExist.get(0);//如果数据库已存在该植物则用数据库的数据
                else {//数据库没有就去查询
                    String detail = getPlantDetail(element.children().get(4).child(0).attr("href"));
                    String plantImageUrl = getPlantImageUrl(element.children().get(3).text());
                    String plantkind = element.children().get(1).text();
                    if (plantkind.equals("")) plantkind = plantname;
                    plant = Plant.builder().kind(plantkind).name(plantname).detail(detail).pic(plantImageUrl).build();

                    plantRepository.save(plant);
                }
                plantList.add(plant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Method.ReducePlant(plantList);
    }

    public String getPlantDetail(String url) {
        url = String.format("http://db.kib.ac.cn/CNFlora/%s", url);
        Connection connection = Jsoup.connect(url);
        String result = null;
        try {
            result = connection.get().getElementById("ContentPlaceHolder1_lbl_fulltext").text().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getPlantImageUrl(String name) {
        /*String url = String.format("http://www.plant.csdb.cn/api.php?ntype=chname&name=%s",name);
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByTag("item");
            if (elements.size() == 0){
                return null;
            }
            String detailUrl = elements.get(0).getElementsByTag("source").text();
            return Jsoup.connect(detailUrl).get().getElementsByTag("img").get(0).attr("src");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }*/
        int number = 5;
        int timeOut = 5000;
        String url = "http://image.baidu.com/search/avatarjson?tn=resultjsonavatarnew&ie=utf-8&word=" + name + "&cg=star&pn=" + 0 + "&rn=" + 5 + "&itg=0&z=0&fr=&width=&height=&lm=-1&ic=0&s=0&st=-1&gsm=" + Integer.toHexString(0);
        Document document;
        try {
            document = Jsoup.connect(url).data("query", "Java")//请求参数
                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                    .timeout(timeOut)
                    .get();
            String xmlSource = document.toString();
            return dealResult(xmlSource);
        } catch (Exception e) {
            return "http://qnimg.zowoyoo.com/img/15463/1509533934407.jpg";
        }
    }

    private static String dealResult(String xmlSource) {
        String imageURL = "";
        xmlSource = StringEscapeUtils.unescapeHtml3(xmlSource);
        String reg = "objURL\":\"http://.+?\\.(gif|jpeg|png|jpg|bmp|jfif)";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(xmlSource);
        while (m.find()) {

            imageURL = m.group().substring(9);
            String reg2 = ".*fromURL.*";
            if (imageURL.matches(reg2)) continue;
            if ("".equals(imageURL)) {
                imageURL = "http://qnimg.zowoyoo.com/img/15463/1509533934407.jpg";
            }
            break;
        }
        return imageURL;
    }


    public List<ReducePlantsResponse> searchPlant(String name) {
        return getPlantData(name);
    }

}