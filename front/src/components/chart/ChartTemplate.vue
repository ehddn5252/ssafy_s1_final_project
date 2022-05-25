<template>
  <div>
    <chart-example
      :dataKind="dataKind"
      :avgs="avgs"
      :names="names"
      :lats="lats"
      :lngs="lngs"
    />
  </div>
</template>
<script>
import ChartExample from "@/components/chart/ChartExample.vue";
import http from "@/api/http";
export default {
  components: {
    ChartExample,
  },
  datas: {
    avgs: new Array(),
    names: new Array(),
    lats: new Array(),
    lngs: new Array(),
  },
  props: {
    dataKind: String,
    regionCode: String,
  },
  created() {
    this.avgs = new Array();
    this.names = new Array();
    this.lats = new Array();
    this.lngs = new Array();
    console.log("this.regionCode");
    console.log(this.regionCode);
    if (this.regionCode == null) {
      console.log("this is null case");
      http
        .get(`/avg/${this.dataKind}`)
        .then(({ data }) => {
          console.log("data length");
          console.log(data.length);
          for (let i = 0; i < data.length; ++i) {
            this.avgs.push(data[i].avg);
            this.names.push(data[i].name);
            this.lats.push(data[i].lat);
            this.lngs.push(data[i].lng);
          }
        })
        .catch((e) => {
          console.log("e");
          console.log(e);
        });
    } else {
      console.log("this is not null case");
      let params = {};
      let paramKey = "sido";
      if (this.dataKind == "dong") {
        paramKey = "gugun";
      }
      params[paramKey] = this.regionCode;
      console.log(params);
      http
        .get(`/avg/${this.dataKind}/specific`, { params })
        .then(({ data }) => {
          console.log("data?");
          console.log(data[0]);
          console.log(data.length);
          for (let i = 0; i < data.length; ++i) {
            this.avgs.push(data[i].avg);
            this.names.push(data[i].name);
            this.lats.push(data[i].lat);
            this.lngs.push(data[i].lng);
          }
        })
        .catch((e) => {
          console.log("e");
          console.log(e);
        });
    }
  },
  methods: {},
};
</script>
<style lang=""></style>
