<template>
  <div class="app-container">
    <div class="mapBox" id="container">

    </div>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      
    };
  },
  created() {
  },
  mounted(){
    var map = new BMap.Map('container')
    var point = new BMap.Point(110.424914, 25.317862)
    map.centerAndZoom(point, 17)
    map.enableScrollWheelZoom(true)

    var scaleCtrl = new BMap.ScaleControl();  // 添加比例尺控件
    map.addControl(scaleCtrl);

    var point = new BMap.Point(110.426223, 25.318017);   
    var marker = new BMap.Marker(point);        // 创建标注   
    map.addOverlay(marker);                     // 将标注添加到地图中

    var point = new BMap.Point(110.423133, 25.318308);   
    var marker = new BMap.Marker(point);        // 创建标注   
    map.addOverlay(marker);                     // 将标注添加到地图中

     var point = new BMap.Point(110.421974, 25.316873);   
    var marker = new BMap.Marker(point);        // 创建标注   
    map.addOverlay(marker);                     // 将标注添加到地图中

     var point = new BMap.Point(110.424678, 25.316291);   
    var marker = new BMap.Marker(point);        // 创建标注   
    map.addOverlay(marker);                     // 将标注添加到地图中

  },
  methods: {
      
  }
};
</script>

<style scoped lang="scss">
  .mapBox {
    height: 800px;
    width: 100%;
  }
</style>