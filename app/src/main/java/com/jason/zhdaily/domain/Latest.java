package com.jason.zhdaily.domain;

import java.util.List;

public class Latest {

    /**
     * date : 20180514
     * stories : [{"title":"《复联 3》的画面看起来那么紫，真不是我灭霸的锅","ga_prefix":"051414","images":["https://pic1.zhimg.com/v2-c2536ea5a449cb8d514c759ede07a20c.jpg"],"multipic":true,"type":0,"id":9682512},{"images":["https://pic2.zhimg.com/v2-00b4f0314470212f6550f367bb724679.jpg"],"type":0,"id":9682515,"ga_prefix":"051412","title":"被「悬赏」的顺风车司机刘振华"},{"images":["https://pic1.zhimg.com/v2-df5b339c830b5b95e74c80fc411a188c.jpg"],"type":0,"id":9682489,"ga_prefix":"051412","title":"大误 · 灭霸老爹的不归之路（有剧透）"},{"images":["https://pic1.zhimg.com/v2-6e94b422f748674e0660e69480bf777c.jpg"],"type":0,"id":9682562,"ga_prefix":"051410","title":"拔牙时感到疼痛，千万别忍着"},{"images":["https://pic1.zhimg.com/v2-87157c8cabae92d36a6eb8c14045fe78.jpg"],"type":0,"id":9682193,"ga_prefix":"051409","title":"万一你以后有了自己的家，装修时千万别和我犯同样的错误"},{"images":["https://pic3.zhimg.com/v2-910312326beb3651e729b8a0f213aaee.jpg"],"type":0,"id":9682012,"ga_prefix":"051408","title":"摩拜被收购后，回头再看它的诞生成长，活脱脱一部启示录"},{"title":"你所爱的表情包们已经老了","ga_prefix":"051407","images":["https://pic3.zhimg.com/v2-c06bf02582b22e10b4fc2936d6055992.jpg"],"multipic":true,"type":0,"id":9682257},{"images":["https://pic3.zhimg.com/v2-9c01eeb776cd5e1ed85186be1504d38a.jpg"],"type":0,"id":9682399,"ga_prefix":"051407","title":"错一次损失几百万，一个容错率几乎为零的职业"},{"images":["https://pic2.zhimg.com/v2-b0e80f207e29d099c11410a939ab37d5.jpg"],"type":0,"id":9682522,"ga_prefix":"051406","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-ed5ebb65cbadaab28e4aa253dbdabfe9.jpg","type":0,"id":9682512,"ga_prefix":"051414","title":"《复联 3》的画面看起来那么紫，真不是我灭霸的锅"},{"image":"https://pic2.zhimg.com/v2-6123abf6eb806373d0ff0540d219bff5.jpg","type":0,"id":9682515,"ga_prefix":"051412","title":"被「悬赏」的顺风车司机刘振华"},{"image":"https://pic4.zhimg.com/v2-50c6de1a501741def7c9a836d9e90b2f.jpg","type":0,"id":9682562,"ga_prefix":"051410","title":"拔牙时感到疼痛，千万别忍着"},{"image":"https://pic4.zhimg.com/v2-dced7cf100a3fc8543e66e95b834af2f.jpg","type":0,"id":9682449,"ga_prefix":"051312","title":"爱奇艺的 HR 要「过滤河南人」，但被伤害的不只河南人"},{"image":"https://pic3.zhimg.com/v2-d626b7fff1a2dbe5c82395d7e3f5ccb2.jpg","type":0,"id":9682468,"ga_prefix":"051309","title":"本周热门精选 · 一些神奇发现"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * title : 《复联 3》的画面看起来那么紫，真不是我灭霸的锅
         * ga_prefix : 051414
         * images : ["https://pic1.zhimg.com/v2-c2536ea5a449cb8d514c759ede07a20c.jpg"]
         * multipic : true
         * type : 0
         * id : 9682512
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-ed5ebb65cbadaab28e4aa253dbdabfe9.jpg
         * type : 0
         * id : 9682512
         * ga_prefix : 051414
         * title : 《复联 3》的画面看起来那么紫，真不是我灭霸的锅
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
