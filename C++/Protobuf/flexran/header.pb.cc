// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: header.proto

#include "header.pb.h"

#include <algorithm>

#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/wire_format_lite.h>
#include <google/protobuf/descriptor.h>
#include <google/protobuf/generated_message_reflection.h>
#include <google/protobuf/reflection_ops.h>
#include <google/protobuf/wire_format.h>
// @@protoc_insertion_point(includes)
#include <google/protobuf/port_def.inc>
namespace protocol {
class flex_headerDefaultTypeInternal {
 public:
  ::PROTOBUF_NAMESPACE_ID::internal::ExplicitlyConstructed<flex_header> _instance;
} _flex_header_default_instance_;
}  // namespace protocol
static void InitDefaultsscc_info_flex_header_header_2eproto() {
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  {
    void* ptr = &::protocol::_flex_header_default_instance_;
    new (ptr) ::protocol::flex_header();
    ::PROTOBUF_NAMESPACE_ID::internal::OnShutdownDestroyMessage(ptr);
  }
  ::protocol::flex_header::InitAsDefaultInstance();
}

::PROTOBUF_NAMESPACE_ID::internal::SCCInfo<0> scc_info_flex_header_header_2eproto =
    {{ATOMIC_VAR_INIT(::PROTOBUF_NAMESPACE_ID::internal::SCCInfoBase::kUninitialized), 0, 0, InitDefaultsscc_info_flex_header_header_2eproto}, {}};

static ::PROTOBUF_NAMESPACE_ID::Metadata file_level_metadata_header_2eproto[1];
static const ::PROTOBUF_NAMESPACE_ID::EnumDescriptor* file_level_enum_descriptors_header_2eproto[1];
static constexpr ::PROTOBUF_NAMESPACE_ID::ServiceDescriptor const** file_level_service_descriptors_header_2eproto = nullptr;

const ::PROTOBUF_NAMESPACE_ID::uint32 TableStruct_header_2eproto::offsets[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  PROTOBUF_FIELD_OFFSET(::protocol::flex_header, _has_bits_),
  PROTOBUF_FIELD_OFFSET(::protocol::flex_header, _internal_metadata_),
  ~0u,  // no _extensions_
  ~0u,  // no _oneof_case_
  ~0u,  // no _weak_field_map_
  PROTOBUF_FIELD_OFFSET(::protocol::flex_header, version_),
  PROTOBUF_FIELD_OFFSET(::protocol::flex_header, type_),
  PROTOBUF_FIELD_OFFSET(::protocol::flex_header, xid_),
  0,
  1,
  2,
};
static const ::PROTOBUF_NAMESPACE_ID::internal::MigrationSchema schemas[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) = {
  { 0, 8, sizeof(::protocol::flex_header)},
};

static ::PROTOBUF_NAMESPACE_ID::Message const * const file_default_instances[] = {
  reinterpret_cast<const ::PROTOBUF_NAMESPACE_ID::Message*>(&::protocol::_flex_header_default_instance_),
};

const char descriptor_table_protodef_header_2eproto[] PROTOBUF_SECTION_VARIABLE(protodesc_cold) =
  "\n\014header.proto\022\010protocol\"9\n\013flex_header\022"
  "\017\n\007version\030\001 \001(\r\022\014\n\004type\030\002 \001(\r\022\013\n\003xid\030\004 "
  "\001(\r*\237\004\n\tflex_type\022\016\n\nFLPT_HELLO\020\000\022\025\n\021FLP"
  "T_ECHO_REQUEST\020\001\022\023\n\017FLPT_ECHO_REPLY\020\002\022\023\n"
  "\017FLPT_DISCONNECT\020\024\022\026\n\022FLPT_STATS_REQUEST"
  "\020\003\022\024\n\020FLPT_STATS_REPLY\020\004\022\023\n\017FLPT_SF_TRIG"
  "GER\020\005\022\023\n\017FLPT_UL_SR_INFO\020\006\022\037\n\033FLPT_GET_E"
  "NB_CONFIG_REQUEST\020\007\022\035\n\031FLPT_GET_ENB_CONF"
  "IG_REPLY\020\010\022\036\n\032FLPT_GET_UE_CONFIG_REQUEST"
  "\020\t\022\034\n\030FLPT_GET_UE_CONFIG_REPLY\020\n\022\036\n\032FLPT"
  "_GET_LC_CONFIG_REQUEST\020\013\022\034\n\030FLPT_GET_LC_"
  "CONFIG_REPLY\020\014\022\026\n\022FLPT_DL_MAC_CONFIG\020\r\022\023"
  "\n\017FLPT_HO_COMMAND\020\025\022\030\n\024FLPT_UE_STATE_CHA"
  "NGE\020\016\022\031\n\025FLPT_DELEGATE_CONTROL\020\017\022\032\n\026FLPT"
  "_RECONFIGURE_AGENT\020\020\022\027\n\023FLPT_RRC_TRIGGER"
  "ING\020\021\022\026\n\022FLPT_UL_MAC_CONFIG\020\022"
  ;
static const ::PROTOBUF_NAMESPACE_ID::internal::DescriptorTable*const descriptor_table_header_2eproto_deps[1] = {
};
static ::PROTOBUF_NAMESPACE_ID::internal::SCCInfoBase*const descriptor_table_header_2eproto_sccs[1] = {
  &scc_info_flex_header_header_2eproto.base,
};
static ::PROTOBUF_NAMESPACE_ID::internal::once_flag descriptor_table_header_2eproto_once;
const ::PROTOBUF_NAMESPACE_ID::internal::DescriptorTable descriptor_table_header_2eproto = {
  false, false, descriptor_table_protodef_header_2eproto, "header.proto", 629,
  &descriptor_table_header_2eproto_once, descriptor_table_header_2eproto_sccs, descriptor_table_header_2eproto_deps, 1, 0,
  schemas, file_default_instances, TableStruct_header_2eproto::offsets,
  file_level_metadata_header_2eproto, 1, file_level_enum_descriptors_header_2eproto, file_level_service_descriptors_header_2eproto,
};

// Force running AddDescriptors() at dynamic initialization time.
static bool dynamic_init_dummy_header_2eproto = (static_cast<void>(::PROTOBUF_NAMESPACE_ID::internal::AddDescriptors(&descriptor_table_header_2eproto)), true);
namespace protocol {
const ::PROTOBUF_NAMESPACE_ID::EnumDescriptor* flex_type_descriptor() {
  ::PROTOBUF_NAMESPACE_ID::internal::AssignDescriptors(&descriptor_table_header_2eproto);
  return file_level_enum_descriptors_header_2eproto[0];
}
bool flex_type_IsValid(int value) {
  switch (value) {
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 20:
    case 21:
      return true;
    default:
      return false;
  }
}


// ===================================================================

void flex_header::InitAsDefaultInstance() {
}
class flex_header::_Internal {
 public:
  using HasBits = decltype(std::declval<flex_header>()._has_bits_);
  static void set_has_version(HasBits* has_bits) {
    (*has_bits)[0] |= 1u;
  }
  static void set_has_type(HasBits* has_bits) {
    (*has_bits)[0] |= 2u;
  }
  static void set_has_xid(HasBits* has_bits) {
    (*has_bits)[0] |= 4u;
  }
};

flex_header::flex_header(::PROTOBUF_NAMESPACE_ID::Arena* arena)
  : ::PROTOBUF_NAMESPACE_ID::Message(arena) {
  SharedCtor();
  RegisterArenaDtor(arena);
  // @@protoc_insertion_point(arena_constructor:protocol.flex_header)
}
flex_header::flex_header(const flex_header& from)
  : ::PROTOBUF_NAMESPACE_ID::Message(),
      _has_bits_(from._has_bits_) {
  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  ::memcpy(&version_, &from.version_,
    static_cast<size_t>(reinterpret_cast<char*>(&xid_) -
    reinterpret_cast<char*>(&version_)) + sizeof(xid_));
  // @@protoc_insertion_point(copy_constructor:protocol.flex_header)
}

void flex_header::SharedCtor() {
  ::memset(&version_, 0, static_cast<size_t>(
      reinterpret_cast<char*>(&xid_) -
      reinterpret_cast<char*>(&version_)) + sizeof(xid_));
}

flex_header::~flex_header() {
  // @@protoc_insertion_point(destructor:protocol.flex_header)
  SharedDtor();
  _internal_metadata_.Delete<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

void flex_header::SharedDtor() {
  GOOGLE_DCHECK(GetArena() == nullptr);
}

void flex_header::ArenaDtor(void* object) {
  flex_header* _this = reinterpret_cast< flex_header* >(object);
  (void)_this;
}
void flex_header::RegisterArenaDtor(::PROTOBUF_NAMESPACE_ID::Arena*) {
}
void flex_header::SetCachedSize(int size) const {
  _cached_size_.Set(size);
}
const flex_header& flex_header::default_instance() {
  ::PROTOBUF_NAMESPACE_ID::internal::InitSCC(&::scc_info_flex_header_header_2eproto.base);
  return *internal_default_instance();
}


void flex_header::Clear() {
// @@protoc_insertion_point(message_clear_start:protocol.flex_header)
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  cached_has_bits = _has_bits_[0];
  if (cached_has_bits & 0x00000007u) {
    ::memset(&version_, 0, static_cast<size_t>(
        reinterpret_cast<char*>(&xid_) -
        reinterpret_cast<char*>(&version_)) + sizeof(xid_));
  }
  _has_bits_.Clear();
  _internal_metadata_.Clear<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>();
}

const char* flex_header::_InternalParse(const char* ptr, ::PROTOBUF_NAMESPACE_ID::internal::ParseContext* ctx) {
#define CHK_(x) if (PROTOBUF_PREDICT_FALSE(!(x))) goto failure
  _Internal::HasBits has_bits{};
  ::PROTOBUF_NAMESPACE_ID::Arena* arena = GetArena(); (void)arena;
  while (!ctx->Done(&ptr)) {
    ::PROTOBUF_NAMESPACE_ID::uint32 tag;
    ptr = ::PROTOBUF_NAMESPACE_ID::internal::ReadTag(ptr, &tag);
    CHK_(ptr);
    switch (tag >> 3) {
      // optional uint32 version = 1;
      case 1:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 8)) {
          _Internal::set_has_version(&has_bits);
          version_ = ::PROTOBUF_NAMESPACE_ID::internal::ReadVarint32(&ptr);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      // optional uint32 type = 2;
      case 2:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 16)) {
          _Internal::set_has_type(&has_bits);
          type_ = ::PROTOBUF_NAMESPACE_ID::internal::ReadVarint32(&ptr);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      // optional uint32 xid = 4;
      case 4:
        if (PROTOBUF_PREDICT_TRUE(static_cast<::PROTOBUF_NAMESPACE_ID::uint8>(tag) == 32)) {
          _Internal::set_has_xid(&has_bits);
          xid_ = ::PROTOBUF_NAMESPACE_ID::internal::ReadVarint32(&ptr);
          CHK_(ptr);
        } else goto handle_unusual;
        continue;
      default: {
      handle_unusual:
        if ((tag & 7) == 4 || tag == 0) {
          ctx->SetLastTag(tag);
          goto success;
        }
        ptr = UnknownFieldParse(tag,
            _internal_metadata_.mutable_unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(),
            ptr, ctx);
        CHK_(ptr != nullptr);
        continue;
      }
    }  // switch
  }  // while
success:
  _has_bits_.Or(has_bits);
  return ptr;
failure:
  ptr = nullptr;
  goto success;
#undef CHK_
}

::PROTOBUF_NAMESPACE_ID::uint8* flex_header::_InternalSerialize(
    ::PROTOBUF_NAMESPACE_ID::uint8* target, ::PROTOBUF_NAMESPACE_ID::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:protocol.flex_header)
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  (void) cached_has_bits;

  cached_has_bits = _has_bits_[0];
  // optional uint32 version = 1;
  if (cached_has_bits & 0x00000001u) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::WriteUInt32ToArray(1, this->_internal_version(), target);
  }

  // optional uint32 type = 2;
  if (cached_has_bits & 0x00000002u) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::WriteUInt32ToArray(2, this->_internal_type(), target);
  }

  // optional uint32 xid = 4;
  if (cached_has_bits & 0x00000004u) {
    target = stream->EnsureSpace(target);
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::WriteUInt32ToArray(4, this->_internal_xid(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = ::PROTOBUF_NAMESPACE_ID::internal::WireFormat::InternalSerializeUnknownFieldsToArray(
        _internal_metadata_.unknown_fields<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(::PROTOBUF_NAMESPACE_ID::UnknownFieldSet::default_instance), target, stream);
  }
  // @@protoc_insertion_point(serialize_to_array_end:protocol.flex_header)
  return target;
}

size_t flex_header::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:protocol.flex_header)
  size_t total_size = 0;

  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  cached_has_bits = _has_bits_[0];
  if (cached_has_bits & 0x00000007u) {
    // optional uint32 version = 1;
    if (cached_has_bits & 0x00000001u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::UInt32Size(
          this->_internal_version());
    }

    // optional uint32 type = 2;
    if (cached_has_bits & 0x00000002u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::UInt32Size(
          this->_internal_type());
    }

    // optional uint32 xid = 4;
    if (cached_has_bits & 0x00000004u) {
      total_size += 1 +
        ::PROTOBUF_NAMESPACE_ID::internal::WireFormatLite::UInt32Size(
          this->_internal_xid());
    }

  }
  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    return ::PROTOBUF_NAMESPACE_ID::internal::ComputeUnknownFieldsSize(
        _internal_metadata_, total_size, &_cached_size_);
  }
  int cached_size = ::PROTOBUF_NAMESPACE_ID::internal::ToCachedSize(total_size);
  SetCachedSize(cached_size);
  return total_size;
}

void flex_header::MergeFrom(const ::PROTOBUF_NAMESPACE_ID::Message& from) {
// @@protoc_insertion_point(generalized_merge_from_start:protocol.flex_header)
  GOOGLE_DCHECK_NE(&from, this);
  const flex_header* source =
      ::PROTOBUF_NAMESPACE_ID::DynamicCastToGenerated<flex_header>(
          &from);
  if (source == nullptr) {
  // @@protoc_insertion_point(generalized_merge_from_cast_fail:protocol.flex_header)
    ::PROTOBUF_NAMESPACE_ID::internal::ReflectionOps::Merge(from, this);
  } else {
  // @@protoc_insertion_point(generalized_merge_from_cast_success:protocol.flex_header)
    MergeFrom(*source);
  }
}

void flex_header::MergeFrom(const flex_header& from) {
// @@protoc_insertion_point(class_specific_merge_from_start:protocol.flex_header)
  GOOGLE_DCHECK_NE(&from, this);
  _internal_metadata_.MergeFrom<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(from._internal_metadata_);
  ::PROTOBUF_NAMESPACE_ID::uint32 cached_has_bits = 0;
  (void) cached_has_bits;

  cached_has_bits = from._has_bits_[0];
  if (cached_has_bits & 0x00000007u) {
    if (cached_has_bits & 0x00000001u) {
      version_ = from.version_;
    }
    if (cached_has_bits & 0x00000002u) {
      type_ = from.type_;
    }
    if (cached_has_bits & 0x00000004u) {
      xid_ = from.xid_;
    }
    _has_bits_[0] |= cached_has_bits;
  }
}

void flex_header::CopyFrom(const ::PROTOBUF_NAMESPACE_ID::Message& from) {
// @@protoc_insertion_point(generalized_copy_from_start:protocol.flex_header)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

void flex_header::CopyFrom(const flex_header& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:protocol.flex_header)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

bool flex_header::IsInitialized() const {
  return true;
}

void flex_header::InternalSwap(flex_header* other) {
  using std::swap;
  _internal_metadata_.Swap<::PROTOBUF_NAMESPACE_ID::UnknownFieldSet>(&other->_internal_metadata_);
  swap(_has_bits_[0], other->_has_bits_[0]);
  ::PROTOBUF_NAMESPACE_ID::internal::memswap<
      PROTOBUF_FIELD_OFFSET(flex_header, xid_)
      + sizeof(flex_header::xid_)
      - PROTOBUF_FIELD_OFFSET(flex_header, version_)>(
          reinterpret_cast<char*>(&version_),
          reinterpret_cast<char*>(&other->version_));
}

::PROTOBUF_NAMESPACE_ID::Metadata flex_header::GetMetadata() const {
  return GetMetadataStatic();
}


// @@protoc_insertion_point(namespace_scope)
}  // namespace protocol
PROTOBUF_NAMESPACE_OPEN
template<> PROTOBUF_NOINLINE ::protocol::flex_header* Arena::CreateMaybeMessage< ::protocol::flex_header >(Arena* arena) {
  return Arena::CreateMessageInternal< ::protocol::flex_header >(arena);
}
PROTOBUF_NAMESPACE_CLOSE

// @@protoc_insertion_point(global_scope)
#include <google/protobuf/port_undef.inc>