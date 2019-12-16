<template>
  <!DOCTYPE html>
  <html lang="en">
  <mdb-container fluid>
    <div>
    <mdb-row>
      <mdb-col col="12">
        <app-header></app-header>
      </mdb-col>
    </mdb-row>
    <mdb-row>
      <mdb-col col="6">
        <app-content></app-content>
      </mdb-col>
      <mdb-col col="6">
        <app-content2></app-content2>
      </mdb-col>
    </mdb-row>
    <mdb-row>
      <mdb-col class="6">
        <app-contenthighchart></app-contenthighchart>
      </mdb-col>
      <mdb-col class="6">

      </mdb-col>
    </mdb-row>
    <mdb-row>
      <mdb-col col="12">
        <app-footer></app-footer>
      </mdb-col>
    </mdb-row>
    </div>
  </mdb-container>


  </html>
</template>
<script>
  import Header from './components/Header.vue';
  import Content from './components/Content.vue';
  import Content2 from './components/Content2.vue';
  import ContentHighchart from './components/ContentHighchart.vue'
  import Footer from './components/Footer.vue';


  import {

  } from 'mdbvue';


  export default {
    name: 'app',
    components: {
      'app-header': Header,
      'app-content': Content,
      'app-footer': Footer,
      'app-content2': Content2,
      'app-contenthighchart': ContentHighchart

    },
    data() {
      return {

      }
    },
    methods: {
      /*getOilData(){
      fetch("mqttfile.json")
      .then(res => res.json()) //json() lest den response returned promise
      .then(data=> (this.oilDataList=data)); //Eine Variable oilDataList erstellt die gefüllt wird mit den Daten vom JSON FILE
   }*/

    }


  };
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap');
</style>