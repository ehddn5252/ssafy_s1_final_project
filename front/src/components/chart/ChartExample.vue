<template>
  <div>
    <canvas id="chart" width="900" height="400"></canvas>
  </div>
</template>
<script>
// import {
//   Chart,
//   BarElement,
//   BarController,
//   LinearScale,
//   CategoryScale,
//   LineElement,
//   LineController,
// } from "chart.js";
import { Chart, registerables } from "chart.js";
Chart.register(...registerables);
// Chart.register(
//   BarElement,
//   BarController,
//   LinearScale,
//   CategoryScale,
//   LineElement,
//   LineController
// );
export default {
  watch: {
    avgs: function () {
      this.fillData(this.avgs, this.names);
    },
  },
  methods: {
    initChart() {},
    fillData(avgs, names) {
      if (this.myChart) {
        this.myChart.destroy();
      }
      console.log("avgs");
      console.log(avgs[0]);
      const ctx = document.getElementById("chart").getContext("2d");
      this.myChart = new Chart(ctx, {
        type: "bar",
        data: {
          labels: names, //["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
          datasets: [
            {
              label: "단위: (만원)",
              data: avgs,
              backgroundColor: [
                "rgba(255, 99, 132, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(255, 206, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(255, 159, 64, 0.2)",
              ],
              borderColor: [
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)",
              ],
              borderWidth: 1,
            },
          ],
        },
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
    },
  },

  mounted() {
    console.log("created in ChartExample.vue");
    this.fillData(this.avgs, this.names);
  },
  props: {
    avgs: Array,
    names: Array,
    lats: Array,
    lngs: Array,
  },
  updated() {
    console.log("fill Data");
  },
  data() {
    return {
      myChart: null,
    };
  },
};
</script>
<style lang=""></style>
