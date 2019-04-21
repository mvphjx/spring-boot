package cn.han.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 队伍图像
 */
@Entity
@Table(name = "t_picture")
public class Picture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 64)
    private String uuid;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    private String path;

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

    public Team getTeam()
    {
        return team;
    }

    public void setTeam(Team team)
    {
        this.team = team;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return "Picture{" + "id=" + id + ", name='" + name + '\'' + ", uuid='" + uuid + '\'' + ", team=" + team
                + ", path='" + path + '\'' + '}';
    }
}
