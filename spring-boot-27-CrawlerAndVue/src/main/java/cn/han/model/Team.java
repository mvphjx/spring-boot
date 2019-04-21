package cn.han.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 最强战队  队伍表
 */
@Entity
@Table(name = "t_team")
public class Team
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true, length = 200)
    private String name;

    @Column(nullable = false, length = 64)
    private String uuid;


    @OneToMany(cascade= CascadeType.ALL,mappedBy="team")
    private List<Picture> pictures;

    @OneToMany(cascade= CascadeType.ALL,mappedBy="team")
    private List<Video> videos;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public List<Picture> getPictures()
    {
        return pictures;
    }

    public void setPictures(List<Picture> pictures)
    {
        this.pictures = pictures;
    }

    public List<Video> getVideos()
    {
        return videos;
    }

    public void setVideos(List<Video> videos)
    {
        this.videos = videos;
    }

    @Override
    public String toString()
    {
        return "Team{" + "id=" + id + ", name='" + name + '\'' + ", uuid='" + uuid + '\'' + ", pictures=" + pictures
                + ", videos=" + videos + '}';
    }



}
