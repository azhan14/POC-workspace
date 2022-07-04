import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if(!value || !args){
      return value;
    }
    return value.filter((item) => {
      return JSON.stringify(item).toLowerCase().includes(args.toLowerCase());
    });
  }

}
