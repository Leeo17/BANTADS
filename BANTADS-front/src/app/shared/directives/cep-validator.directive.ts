import { Directive } from '@angular/core';
import { FormControl, NG_VALIDATORS, Validator } from '@angular/forms';


@Directive({
  selector: '[cepValidator]',
  providers: [{
    provide: NG_VALIDATORS,
    useExisting: CepValidatorDirective,
    multi: true,
  }]
})
export class CepValidatorDirective implements Validator {

  constructor() { }

  validate(c: FormControl) {
    const cep: string = c.value;
    if (cep) {
        cep.length !
        return null;
    } else {
        return { cpfNotValid: true };
    }
    }
    return null;
  }
}