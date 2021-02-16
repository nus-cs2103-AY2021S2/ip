import DefaultTheme from 'vitepress/dist/client/theme-default'
import DownloadLink from '../components/DownloadLink.vue'

export default {
  ...DefaultTheme,
  enhanceApp({ app, router, siteData }) {
    app.component('DownloadLink', DownloadLink)
  }
}