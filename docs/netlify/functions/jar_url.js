const fetch = require('node-fetch');

exports.handler = async function (event, context) {
  const res1 = await fetch('https://api.github.com/repos/lirc572/ip/releases');
  const data1 = await res1.json();
  const res2 = await fetch(data1[0]['assets_url'])
  const data2 = await res2.json();
  console.log(data2);
  return {
    statusCode: 200,
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE',
    },
    body: JSON.stringify(data2[0]['browser_download_url'])
  };
}