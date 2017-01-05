package net.bjyfkj.caa.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YFKJ-1 on 2017/1/5.
 */

public class AdvertisingEntity implements Serializable {

    private List<String> poster;//海报图片,可批量上传(B区)
    private String title;//标题
    private String area_id;//商圈id
    private String promotion_count;//优惠券数量
    private String direction_for_use;//使用说明
    private String content;//活动内容(A区)
    private String item_img;//商品图片(C区)
    private String description;//活动描述(D区)
    private String shop_name;//店铺名称(滚动字幕1)
    private String shop_address;//店铺地址(滚动字幕2)
    private String start_time;//活动开始日期,日期时间戳
    private String end_time;//活动结束日期,日期时间戳
    private String promotion_content;//优惠券内容
    private String promotion_expire;//优惠券有效期(天),默认1天
    private String promotion_get_type;//优惠券领取类型,0每人限领一次,1消费后可再次领取

    public List<String> getPoster() {
        return poster;
    }

    public void setPoster(List<String> poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getPromotion_count() {
        return promotion_count;
    }

    public void setPromotion_count(String promotion_count) {
        this.promotion_count = promotion_count;
    }

    public String getDirection_for_use() {
        return direction_for_use;
    }

    public void setDirection_for_use(String direction_for_use) {
        this.direction_for_use = direction_for_use;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPromotion_content() {
        return promotion_content;
    }

    public void setPromotion_content(String promotion_content) {
        this.promotion_content = promotion_content;
    }

    public String getPromotion_expire() {
        return promotion_expire;
    }

    public void setPromotion_expire(String promotion_expire) {
        this.promotion_expire = promotion_expire;
    }

    public String getPromotion_get_type() {
        return promotion_get_type;
    }

    public void setPromotion_get_type(String promotion_get_type) {
        this.promotion_get_type = promotion_get_type;
    }
}
