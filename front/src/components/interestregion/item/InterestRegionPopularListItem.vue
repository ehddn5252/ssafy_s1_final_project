<template>
  <b-tr>
    <b-td>{{ interestRegion.areaName }}</b-td>
    <b-td>{{ interestRegion.interestCount }}</b-td>
  </b-tr>
</template>

<script>
export default {
  name: "InterestRegionPopularListItem",
  props: {
    interestRegion: Object,
  },
  created: {},
};
</script>

<style></style>
