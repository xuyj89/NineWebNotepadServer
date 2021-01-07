export default{
    get:(vue,url,callback)=>{
        fetch(vue.$store.state.config.baseUrl + url)
        .then((response)=>{
          return response.json();
        })
        .then((myJson)=>{
          callback(myJson);
        });
    }
}