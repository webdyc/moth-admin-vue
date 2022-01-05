<template>
  <div class="phone-box posotopm-relative">
    <img :src="phomeImg" alt="">
    <div class="phone-content-box posotopm-absolute">
      <div class="phone-content" v-html="content" />
    </div>
  </div>
</template>

<script>

export default {
  name: 'PhoneDom',
  props: {
    content: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      phomeImg: require('@/assets/images/phone.jpg')
    }
  }
}
</script>

<style lang="scss" scoped>
.phone-box{
    width: 350px;
    height: 720px;
    overflow: hidden;
    .phone-content-box{
        box-sizing: border-box;
        width: 100%;
        height: 630px;
        padding: 0 15px;
        top: 80px;
        left: 0;
        border-radius: 0 0 14% 14%;
        overflow: hidden;
        .phone-content{
            overflow: hidden;
            overflow-y: auto;
            width: 100%;
            height: 100%;
            background: #ffffff;

        }
    }
}
/* for Chrome */
.phone-content-box::-webkit-scrollbar {
    display: none;
}
</style>

