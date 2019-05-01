package com.han.service.webcrawler.plus;

/**
 * 根据已经入库的team  更新/下载图像
 */

import com.alibaba.fastjson.JSON;
import com.han.contants.Web;
import com.han.jpa.model.Picture;
import com.han.jpa.model.Team;
import com.han.jpa.repository.PicRepository;
import com.han.jpa.repository.TeamRepository;
import com.han.service.webcrawler.plus.json.ImageCrawler;
import com.han.service.webcrawler.plus.json.TeamData;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Json;

import javax.annotation.Resource;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

@Component
public class ImagePipeline implements Pipeline
{
    @Resource
    TeamRepository teamRepository;
    @Resource
    PicRepository picRepository;

    @Override
    public void process(ResultItems resultItems, Task task)
    {
        Json json = resultItems.get("data");
        String groupUuid = resultItems.get("groupUuid");
        Team team = teamRepository.findByUuid(groupUuid);
        boolean isSuccess = false;
        TeamData data = JSON.parseObject(json.get(), TeamData.class);
        List<ImageCrawler> images = data.getImages();
        if (images == null)
        {
            return;
        }
        for (ImageCrawler image : images)
        {
            String path = image.getPath();
            String name = image.getName();
            if (StringUtils.isEmpty(path) || StringUtils.isEmpty(name))
            {
                continue;
            }
            Picture picturePO = new Picture();
            if (team.getPictures() != null)
            {
                for (Picture picture : team.getPictures())
                {
                    if (name.equals(picture.getName()))
                    {
                        picturePO = picture;
                    }
                }
            }
            if (picturePO.getId() == 0)
            {
                picturePO.setTeam(team);
                team.getPictures().add(picturePO);
            }
            if (!StringUtils.isEmpty(picturePO.getPath()))
            {
                continue;
            }
            String uuid = path.substring(path.lastIndexOf("/") + 1, path.indexOf("."));
            String filePath = Web.SAVE_PATH + team.getName() + team.getUuid();
            int result = downloadPicture(Web.IMAGE_URL + path, filePath, image.getName());
            if (result == 1)
            {
                //保存图片成功后 写入数据库
                picturePO.setPath(filePath);
                picturePO.setUuid(uuid);
            }
            else
            {
                Integer failCount = picturePO.getFailCount();
                if (failCount == null)
                {
                    failCount = 1;
                }
                else
                {
                    failCount = failCount + 1;
                }
                picturePO.setFailCount(failCount);
            }
        }
        teamRepository.save(team);
    }

    /**
     * 下载图片
     *
     * @param urlStr 图片地址
     * @param path   保存路径
     * @param name   图片名称
     * @return 1成功
     */
    private int downloadPicture(String urlStr, String path, String name)
    {
        int result = -1;
        try
        {
            URL url = new URL(urlStr);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            String imageName = name + ".jpg";
            File file = new File(path);    //设置下载路径
            if (!file.isDirectory())
            {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path + "\\" + imageName.trim()));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0)
            {
                fileOutputStream.write(buffer, 0, length);
            }
            dataInputStream.close();
            fileOutputStream.close();
            result = 1;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return result;
    }

}
