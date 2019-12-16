<template>
  <div id="app">

    <highcharts :options="options" ref="highcharts"></highcharts> 
    <mdb-btn  @click="getOilData"> Get Temperature</mdb-btn>
    <mdb-btn class="btn btn-amber" @click="updateCredits">update credits</mdb-btn>
  </div>
</template>



<script>
import axios from 'axios'
  export default {
    data() {
      return {
        options: {
          title: {
            text: 'Oil Temperature',
            x: -20 //center
          },
          subtitle: {
            text: 'Line Chart for Oil Temperature',
            x: -20
          },
          xAxis: {
            categories: [0, 10, 20, 30, 40, 50, 60, 70]
          },
          yAxis: {
            title: {
              text: 'Temperature (°C)'
            },
            plotLines: [{
              value: 0,
              width: 1,
              color: '#808080'
            }]
          },
          tooltip: {
            valueSuffix: '°C'
          },
          legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
          },
          series: [{
            name: 'Temperature',
            oilDataList: []
          }]
        }
      }
    },
    methods: {
      updateCredits: function () {
        var chart = this.$refs.highcharts.chart;
        chart.credits.update({
          style: {
            color: '#' + (Math.random() * 0xffffff | 0).toString(16)
          }
        });
      },
      getOilData() {
        axios
          .get("mqttfile.json")
          .then(response => (this.oilDataList = response.data))

      }
    }

  }
</script>
<style scoped>
  input[type="color"]::-webkit-color-swatch-wrapper {
    padding: 0;
  }

  #colorPicker {
    border: 0;
    padding: 0;
    margin: 0;
    width: 30px;
    height: 30px;
  }

  .numberInput {
    width: 30px;
  }
</style>