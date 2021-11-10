package com.test.myapplication.bean;

import java.util.List;

public class PhotoBean {

    /**
     * page
     */
    private int page;
    /**
     * per_page
     */
    private int per_page;
    /**
     * photos
     */
    private List<PhotosDTO> photos;
    /**
     * total_results
     */
    private int total_results;
    /**
     * next_page
     */
    private String next_page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public List<PhotosDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosDTO> photos) {
        this.photos = photos;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public String getNext_page() {
        return next_page;
    }

    public void setNext_page(String next_page) {
        this.next_page = next_page;
    }

    public static class PhotosDTO {
        /**
         * id
         */
        private int id;
        /**
         * width
         */
        private int width;
        /**
         * height
         */
        private int height;
        /**
         * url
         */
        private String url;
        /**
         * photographer
         */
        private String photographer;
        /**
         * photographer_url
         */
        private String photographer_url;
        /**
         * photographer_id
         */
        private int photographer_id;
        /**
         * avg_color
         */
        private String avg_color;
        /**
         * src
         */
        private SrcDTO src;
        /**
         * liked
         */
        private boolean liked;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPhotographer() {
            return photographer;
        }

        public void setPhotographer(String photographer) {
            this.photographer = photographer;
        }

        public String getPhotographer_url() {
            return photographer_url;
        }

        public void setPhotographer_url(String photographer_url) {
            this.photographer_url = photographer_url;
        }

        public int getPhotographer_id() {
            return photographer_id;
        }

        public void setPhotographer_id(int photographer_id) {
            this.photographer_id = photographer_id;
        }

        public String getAvg_color() {
            return avg_color;
        }

        public void setAvg_color(String avg_color) {
            this.avg_color = avg_color;
        }

        public SrcDTO getSrc() {
            return src;
        }

        public void setSrc(SrcDTO src) {
            this.src = src;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public static class SrcDTO {
            /**
             * original
             */
            private String original;
            /**
             * large2x
             */
            private String large2x;
            /**
             * large
             */
            private String large;
            /**
             * medium
             */
            private String medium;
            /**
             * small
             */
            private String small;
            /**
             * portrait
             */
            private String portrait;
            /**
             * landscape
             */
            private String landscape;
            /**
             * tiny
             */
            private String tiny;

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public String getLarge2x() {
                return large2x;
            }

            public void setLarge2x(String large2x) {
                this.large2x = large2x;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getPortrait() {
                return portrait;
            }

            public void setPortrait(String portrait) {
                this.portrait = portrait;
            }

            public String getLandscape() {
                return landscape;
            }

            public void setLandscape(String landscape) {
                this.landscape = landscape;
            }

            public String getTiny() {
                return tiny;
            }

            public void setTiny(String tiny) {
                this.tiny = tiny;
            }
        }
    }
}
