package com.gupiao.Shares.tentop;

public  class TopTenEntity{
        private String rank;//����
        private String code;//��Ʊ����
        private String name;//��Ʊ���
        private String settlement;//���̼�
        private String highDownRange;//�ǵ�����%��
        private String highDownAmount;//�ǵ���(��Ԫ)
        private String bugAmount;//������(��Ԫ)
        private String sellAmount;//�������(��Ԫ)
        private String cleanAmount;//�����(��Ԫ)
        private String dealAmount;//�ɽ����(��Ԫ)

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSettlement() {
            return settlement;
        }

        public void setSettlement(String settlement) {
            this.settlement = settlement;
        }

        public String getHighDownRange() {
            return highDownRange;
        }

        public void setHighDownRange(String highDownRange) {
            this.highDownRange = highDownRange;
        }

        public String getHighDownAmount() {
            return highDownAmount;
        }

        public void setHighDownAmount(String highDownAmount) {
            this.highDownAmount = highDownAmount;
        }

        public String getBugAmount() {
            return bugAmount;
        }

        public void setBugAmount(String bugAmount) {
            this.bugAmount = bugAmount;
        }

        public String getSellAmount() {
            return sellAmount;
        }

        public void setSellAmount(String sellAmount) {
            this.sellAmount = sellAmount;
        }

        public String getCleanAmount() {
            return cleanAmount;
        }

        public void setCleanAmount(String cleanAmount) {
            this.cleanAmount = cleanAmount;
        }

        public String getDealAmount() {
            return dealAmount;
        }

        public void setDealAmount(String dealAmount) {
            this.dealAmount = dealAmount;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }