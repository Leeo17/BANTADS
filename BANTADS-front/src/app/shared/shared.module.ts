import { CommonModule } from '@angular/common';
import { NgModule } from "@angular/core";
import { CpfValidatorDirective } from "./directives";

@NgModule({
    declarations: [
        CpfValidatorDirective
    ],
    imports: [CommonModule],
    exports: [
        CpfValidatorDirective
    ]
})
export class SharedModule {
}