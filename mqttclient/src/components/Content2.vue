<template>
    <div>
        <h1>Search</h1>
        <hr>
        <mdb-input v-model="search" label="Search Temperature.."></mdb-input>

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
                        <th> Oil Temperature</th>
                        <th> Unit</th>
                        <th> Oil Pressure</th>
                        <th> Viscosity</th>
                        <th> Temperature</th>
                        <th> Latitude</th>
                        <th> Longitude</th>
                        <th> Counter</th>

                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(oilData, index) in filteredList" :key="index">
                        <td>{{oilData.oiltemperature}} °C</td>
                        <td>{{oilData.unit}}</td>
                        <td v-bind:style='{" oil-pressure": $data["oil-pressure"] }'> </td>
                        <!-- Problem mit Hyphen 
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
                currentSortDir: 'asc',
                search: ""
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
            },
            filteredList() {
                this.getOilData()
                return this.oilDataList.filter(oilData => {
                     return oilData.oiltemperature == this.search
                })
            }
            /*     filteredList() {
      return this.postList.filter(post => {
        return post.title.toLowerCase().includes(this.search.toLowerCase())
      }) */
        }
    }
</script>


<style scoped>


</style>