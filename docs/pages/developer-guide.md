# Developer Guide

Lorum ipsum

## Development Environment Setup

We recommend developing Elaina with [IntelliJ Idea](#setup-for-intellij-idea) or [Visual Studio Code](#setup-for-visual-studio-code). Depending on which one you prefer, follow one of the sections below.

### Setup for IntelliJ Idea

Lorum ipsum

### Setup for Visual Studio Code

Lorum ipsum

## Design

Lorum ipsum

## Implementation

Lorum ipsum

## Documentation

Elaina uses [VitePress](https://vitepress.vuejs.org/) for documentation. The documentation sources are under the `/docs` directory. 

::: tip Notes
[Node.js](https://nodejs.org/) version >= 12.0.0 is required to preview or build the documentation.

We use `yarn` to manage Node dependencies, but if you have a strong reason to use `npm`, just go ahead and try.
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

### DevOps

Lorem ipsum
