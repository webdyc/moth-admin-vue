<template>
  <svg class="svg-class" :class="className">
    <use :href="'#icon-' + iconName"></use>
  </svg>
</template>
<script>
export default {
  name: 'SvgIcon',
  props: {
    iconName: {
      type: String,
      default: ''
    },
    className: {
      type: String,
      default: ''
    }
  },
  setup() {}
}
</script>
