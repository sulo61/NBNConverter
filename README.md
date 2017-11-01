# NBNConverter
## Number-Byte-Number Converter
Language: Kotlin

Module supporting conversions between numbers and bytes

### Available conversions in current version:
1. Int (0 - 255) <- into ->  1 byte
2. Int (-127 - 127)  <- into ->  1 byte
3. Int (0 - 65535) <- into ->  2 bytes
4. Int (-32767 - 32767)  <- into ->  2 bytes
5. Int (-2147483647 - 2147483647)  <- into ->  4 bytes

### Functions:
Ad 1. 
 - unsigned255NumberToByte
 - byteToUnsigned255Number
 
Ad 2. 
 - signed127NumberToByte 
 - byteToSigned127Number
 
Ad 3. 
 - unsigned65535NumberToBytes
 - bytesToUnsigned65535Number
 
Ad 4. 
 - signed32767ToBytes
 - bytesToSigned32767Number
 
Ad 5. 
 - signed2147483647ToBytes
 - bytesToSigned2147483647Number
 
 
## License
```
Copyright 2017 Michał Sułek

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
