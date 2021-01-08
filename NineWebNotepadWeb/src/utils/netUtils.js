import Config from '../config';
export default{
    get:(vue,url,callback)=>{
        fetch(Config.baseUrl + url)
        .then((response)=>{
          return response.json();
        })
        .then((myJson)=>{
          callback(myJson);
        });
    },
    post:(vue,url,callback,body)=>{
      let requestParam = {
        cache: 'no-cache',
        headers: {
          'content-type': 'application/json'
        },
        method: 'POST'
      };
      if(body){
        requestParam.body = JSON.stringify(body);
      }

      fetch(Config.baseUrl + url,requestParam)
        .then((response)=>{
          return response.json();
        })
        .then((myJson)=>{
          callback(myJson);
        });
    }
}