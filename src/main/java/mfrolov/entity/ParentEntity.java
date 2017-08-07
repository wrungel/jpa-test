package mfrolov.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.eclipse.persistence.annotations.AdditionalCriteria;

@Entity
public class ParentEntity {
    @Id
    private Long id;

    private String description;

    @Version
    private Long version;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
    private List<ChildEntity> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ChildEntity> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public Long getVersion() {
        return version;
    }
}
