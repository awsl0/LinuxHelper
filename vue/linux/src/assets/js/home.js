function Switch(){
  $("[name='test']").bootstrapSwitch({
    onText:"ON",
    offText:"OFF",
    onColor:"success",
    offColor:"danger",
    size:"small",
    onSwitchChange:function(event,state){
      if(state==true){
        console.log('已打开');
      }else{
        console.log('已关闭');
      }
    }
  });
}
