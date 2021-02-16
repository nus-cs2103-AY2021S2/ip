# Developer Guide

## Development Environment Setup

We recommend developing Elaina with [IntelliJ Idea](#setup-for-intellij-idea) or [Visual Studio Code](#setup-for-visual-studio-code). Depending on which one you prefer, follow one of the sections below.

### Setup for IntelliJ Idea

See [Setting up in IntelliJ](https://github.com/lirc572/ip#setting-up-in-intellij)

### Setup for Visual Studio Code

See [Setting up in Visual Studio Code](https://github.com/lirc572/ip#setting-up-in-visual-studio-code)

## Documentation

Elaina's documentation uses [VitePress](https://vitepress.vuejs.org/), hosted on [Netlify](https://www.netlify.com/). The documentation sources are under the `/docs` directory. 

VitePress is used so that we can add custom [Vue.js components](https://vuejs.org/v2/guide/single-file-components.html) in the documentation. VitePress is simply awesome. We recommand using it for all future technical documentations. 

We use a simple [Netlify function](https://docs.netlify.com/functions/overview/) to create an API that provides a dowloadable link to the latest JAR release of Elaina.

::: tip Notes
[Node.js](https://nodejs.org/) version >= 12.0.0 is required to preview or build the documentation.

We use `yarn` to manage Node dependencies, but if you have a strong reason to use `npm` instead, make sure you do not commit your `package-lock.json`.
:::

### Install Node Dependencies

```bash
# cd to the /docs directory:
cd docs
# Install Node dependencies:
yarn install
```

### Run a Development Server of the Documentation

```bash
# Under the /docs directory

yarn dev
```

Then follow your command line output to open one of the preview URLs in your browser.

### Build the Documentation

```bash
# Under the /docs directory

yarn build
```

Then the static website will be generated under the `/docs/.vitepress/dist` directory.

### Preview the Built Documentation

```bash
# Under the /docs directory
# Make sure you run yarn build before this

yarn serve
```

It would launch a server that serves the content under the `/docs/.vitepress/dist` directory.

### Clean the Build Files

```bash
# Under the /docs directory

yarn clean
```

### Things to Note

`docs/.vitepress/components/DownloadLink.vue` is a Vue.js component that calles our Netlify function to get the link to the latest release of Elaina executable.

`docs/netlify/functions/jar_url.js` is the Netlify function that fetches the URL to the latest release of Elaina executable using GitHub REST API.

## DevOps

We use [GitHub Actions](https://docs.github.com/actions) for automated testing and release. You are advised to learn the basics of GitHub Actions before making changes to the GitHub workflows.

### Continuous Integration

- Checkstyle:
  - `.github/workflows/checkstyle.yml` runs checkstyle on push
- Unit testing:
  - `.github/workflows/unit-test.yml` runs unit tests with JUnit on push
- Create release:
  - `.github/workflows/package.yml` creates a new release with an executable JAR file on push of tags with the name of `v*`

### Create a Release

To create a new release, simply create a new tag with version number in the format of `v*`, then push the tag to GitHub. GitHub Actions will handle the rest for you~

```bash
git tag v1.0
git push origin v1.0
```
