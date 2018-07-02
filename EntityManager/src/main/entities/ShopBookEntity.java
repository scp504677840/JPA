package main.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "shop_book", schema = "lab", catalog = "lab")
public class ShopBookEntity {
    private BigInteger id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private BigInteger shopId;
    private BigInteger bookId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "gmt_create", nullable = false)
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @Column(name = "gmt_modified", nullable = false)
    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Basic
    @Column(name = "shop_id", nullable = false)
    public BigInteger getShopId() {
        return shopId;
    }

    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "book_id", nullable = false)
    public BigInteger getBookId() {
        return bookId;
    }

    public void setBookId(BigInteger bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopBookEntity that = (ShopBookEntity) o;
        return id == that.id &&
                shopId == that.shopId &&
                bookId == that.bookId &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gmtCreate, gmtModified, shopId, bookId);
    }

    @Override
    public String toString() {
        return "ShopBookEntity{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", shopId=" + shopId +
                ", bookId=" + bookId +
                '}';
    }
}
