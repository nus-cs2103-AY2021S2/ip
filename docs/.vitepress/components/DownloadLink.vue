<template>
  <div>Please download the latest release of Elaina (<code>elaina.jar</code>) from <a :href=url>here</a>~</div>
</template>

<script>
export default {
  data() {
    return {
      url: null
    };
  },
  mounted() {
    fetch('https://elaina.lirc572.com/.netlify/functions/jar_url')
      .then(r => r.json())
      .then(url => {
        this.url = url;
      });
  }
}
</script>