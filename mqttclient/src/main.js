import Vue from 'vue'
import App from './App.vue'

import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'mdbvue/lib/css/mdb.min.css'
import '@fortawesome/fontawesome-free/css/all.min.css'
import HighchartsVue from 'highcharts-vue'
import Highcharts from "highcharts"
import dataModule from "highcharts/modules/data"
import * as mdbvue from 'mdbvue'
for (const component in mdbvue) {
  Vue.component(component, mdbvue[component])
}


dataModule(Highcharts);

Vue.use(HighchartsVue);

Vue.config.productionTip = false
Vue.use(BootstrapVue);
Vue.use(HighchartsVue);

new Vue({
  render: h => h(App)
}).$mount('#app')
