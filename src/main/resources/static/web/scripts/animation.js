  window.onscroll = function(){
      console.log(document.documentElement.scrollTop);
      if(document.documentElement.scrollTop > 100) {
        document.querySelector('.go-top-container').classList.add('showButton');

      }
      else{
        document.querySelector('.go-top-container').classList.remove('showButton');
      }
    }

    document.querySelector('.go-top-container').addEventListener('click', () => {
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    });
