<template>
  <div>
    <h1>Oil Data</h1>
    <hr>
    <mdb-btn v-on:click="getOilData()">Get Oil Data</mdb-btn>

    <!-- <div v-for="(oilData, index) in oilDataList" :key="index" class="weather-data">
      <div class="weather-stats"> aaaaaaaa
        <div>
          <span>{{oilData.oiltemperature}} AAAAAAAAAAAAA</span>
        </div>
        <div>
          <span class="location">{{oilData.unit}} AAXJO</span>
        </div>
      </div>
      <div class="weather-temp">
     SIGNIERGNIERNIE
      </div>
    </div>-->
    <div class="container">
      <table class="table table-striped">
        <thead>
          <tr>

            <th class="hoverable" @click="sort('oiltemperature')"> Oil Temperature </th>
            <th @click="sort('unit')"> Unit</th>
            <th @click="sort('oilpressure')"> Oil Pressure</th>
            <th @click="sort('viscosity')"> Viscosity</th>
            <th @click="sort('temperature')"> Temperature</th>
            <th @click="sort('latitude')"> Latitude</th>
            <th @click="sort('longitude')"> Longitude</th>
            <th @click="sort('counter')"> Counter</th>

          </tr>
        </thead>
        <tbody>
          <tr v-for="(oilData, index) in sortedData" :key="index">
            <td>{{oilData.oiltemperature}} °C</td>
            <td>{{oilData.unit}}</td>
            <td v-bind:style='{" oil-pressure": $data["oil-pressure"] }' > </td> <!-- Problem mit Hyphen 
            https://stackoverflow.com/questions/43424104/how-bind-data-in-vue-js-when-my-key-consists-dash-in-json-data -->
            <td>{{oilData.viscosity}}</td>
            <td>{{oilData.temperature}} °C</td>
            <td>{{oilData.latitude}}</td>
            <td>{{oilData.longitude}}</td>
            <td>{{oilData.counter}}</td>

          </tr>
        </tbody>
      </table>
      <!--debug: sort={{currentSort}},dir={{currentSortDir}}-->
    </div>


  </div>



</template>

<script>
  import axios from 'axios'

  export default {
    data() {
      return {
        oilDataList: [],
        currentSort: 'name',
        currentSortDir: 'asc'
      }
    },
    components: {
    
    },
    methods: {
      getOilData() {
        //   fetch("weather.json")
        //     .then(response => response.json())
        //     .then(data => (this.weatherDataList = data));
        axios
          .get("mqttfile.json")
          .then(response => (this.oilDataList = response.data))

      },

      mounted() {
        this.getData();

      },
      sort: function (s) {
        //if s == current sort, reverse
        if (s === this.currentSort) {
          this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
        }
        this.currentSort = s;
      }

    },
    computed: {
      sortedData: function () {
        return this.oilDataList.slice().sort((a, b) => {
          let modifier = 1;
          if (this.currentSortDir === 'desc') modifier = -1;
          if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
          if (a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
          return 0;
        });
      }
    }
  }
</script>


<style scoped>


</style>