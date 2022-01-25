package com.littlemoth.common.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.littlemoth.common.core.domain.entity.SysDept;
import com.littlemoth.common.core.domain.entity.TbSysMenu;

/**
 * Treeselect树结构实体类
 * 
 * @author xiusan
 */
public class TreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;
    /** 节点ID */
    private String value;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect()
    {

    }


    public TreeSelect(TbSysMenu menu)
    {
        this.id = menu.getId();
        this.value = menu.getParentIds();
        this.label = menu.getTitle();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(TbSysOffice office) {
        this.id = office.getId();
        this.value = office.getParentIds();
        this.label = office.getName();
        this.children = office.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelect> children)
    {
        this.children = children;
    }
}
