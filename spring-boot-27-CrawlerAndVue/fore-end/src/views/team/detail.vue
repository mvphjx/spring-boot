<template>
  <div class="app-container">
    <div>{{team.name}}</div>
    <div>{{team.uuid}}</div>
    <div v-for="video in team.videos" :key="video.id">
      {{video.path}}
    </div>
    <div v-for="picture in team.pictures" :key="picture.id">
      {{picture.path}}
    </div>

  </div>
</template>

<script>
  import {getTeam} from '@/api/team'

  export default {
    components: {},
    data() {
      return {
        team: null
      }
    },
    created() {
      const uuid = this.$route.params && this.$route.params.uuid
      this.fetchData(uuid)
    },
    methods: {
      fetchData(uuid) {
        this.listLoading = true
        getTeam(uuid).then(response => {
          this.team = response.data
        })
      }
    }
  }
</script>
