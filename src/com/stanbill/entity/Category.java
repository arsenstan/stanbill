package com.stanbill.entity;
/*

实体类Entity 是用于映射表中的一条一条数据的。
比如分类表Category，有很多条分类记录，那么就有一个类也叫做Category，这个类的一个一个的实例，就对应了表Category中的一条一条的记录。

消费分类Category
消费分类Category有3个字段
id 主键
name 名称
recordNumber 消费记录数

回头看看消费分类表 category的表结构，可以发现消费记录数 recordNumber 这个属性并不会出现在数据库中的。 它是根据这种分类在消费记录表Record中有多少条对应信息，临时查出来的。

在设计表结构的时候，这样的字段是不建议存放在数据库中的。 假设在表Category中设计了这么一个字段recordNumber，
那么关于这种分类对应的消费记录数，其实在数据库中有两个：一是这个字段，二是实际Record表中存在的数据数量。

一个信息，在两个地方存在，就需要增加数据一致性的维护成本，也存在数据出现不一致的风险(比如忘记更新recordNumber中的信息)。
 */
public class Category {
    public int id;
    public String name;

    public int recordNumber;

    public int getRecordNumber() {
        return recordNumber;
    }
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //这里提供了一个toString方法，在后续JComboBox中显示的时候，会调用此方法，用于显示分类名称
    public String toString(){
        return name;
    }
}